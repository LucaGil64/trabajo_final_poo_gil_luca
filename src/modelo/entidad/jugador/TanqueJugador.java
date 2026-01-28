package modelo.entidad.jugador;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.EstadoJuego;
import modelo.Mapa;
import modelo.entidad.Entidad;
import modelo.entidad.HiloAnimacionGrande;
import modelo.proyectil.Proyectil;

public class TanqueJugador extends Entidad{

    EstadoTanque estadoTanque;

    private long finInvencibilidad;

    public TanqueJugador (int posX, int posY) {
        
        super(posX, posY, 16, 16, ControladorSprites.getSprite(0, 0, 16, 16), Direccion.ARRIBA, 0.2, 1, 800 , 1, false, null);
        
        
        this.estadoTanque = new EstadoNivel1();
        this.cargarSprites();
    }

    public void subirNivel() {
        System.out.println("nivel subido");
        this.estadoTanque = this.estadoTanque.getSiguienteEstado();
        this.cargarSprites();
        
        setDireccion(this.direccion); // Para que se actualize al instante el nuevo sprite
    }


    @Override
    protected Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño) {
        return this.estadoTanque.crearProyectil(x, y, direccion, this);
    }

    @Override
    protected long getCooldownDisparo() {
        return this.estadoTanque.getCooldownDisparo();
    }

    @Override
    protected double getVelocidadMovimiento() {
        return this.estadoTanque.getVelocidadMovimiento();
    }

    @Override
    protected void cargarSprites() {
        int[] c = this.estadoTanque.getSpritesTanque();
        this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);
    }

    public void inputMovimiento() {

    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    @Override
    public void destruir() {
        if (this.esInvencible()) return; // Si es invencible no se destruye nada

        Thread hiloAnimacion = new Thread(new HiloAnimacionGrande(this.posX, this.posY));
        hiloAnimacion.start();

        EstadoJuego.getInstance().restarVida();
        this.existe = false;

        System.out.println("Se destruyo al TanqueJugador");
        
        if (EstadoJuego.getInstance().esGameOver()) {
            System.out.println("GAME OVER - PUNTUACION FINAL: " + EstadoJuego.getInstance().getPuntaje());

        } else {
            Mapa.getInstance().respawnJugador(true);
        }
        
    }

    public void resetearTotal() {
        this.existe = true;
        this.estadoTanque = new EstadoNivel1();
        this.cargarSprites();

        this.posX = 80;
        this.xPrecisa = 80;
        this.posY = 200;
        this.yPrecisa = 200;

        this.sprite = ControladorSprites.getSprite(0, 0, 16, 16);
        this.direccion = Direccion.ARRIBA; // TODO: VER CUAL DE LOS 2 ES NECESARIO
        this.setDireccion(Direccion.ARRIBA);
        
        
    }

    public void resetearPosicion() {
        this.existe = true;

        this.posX = 80;
        this.xPrecisa = 80;
        this.posY = 200;
        this.yPrecisa = 200;

        setDireccion(Direccion.ARRIBA);
    }

    public void activarInvencibilidad(long duracion) {
        this.finInvencibilidad = System.currentTimeMillis() + duracion;
    }

    public boolean esInvencible() {
        return System.currentTimeMillis() < this.finInvencibilidad;
    }

}
