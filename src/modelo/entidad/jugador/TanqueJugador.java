package modelo.entidad.jugador;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;

public class TanqueJugador extends Entidad{

    EstadoTanque estadoTanque;

    public TanqueJugador (int posX, int posY) {
        
        super(posX, posY, 16, 16, ControladorSprites.getSprite(0, 0, 16, 16), Direccion.ARRIBA, 0.2, 1, 800 , 0, false, null);
        
        
        this.estadoTanque = new EstadoNivel1();
        this.cargarSprites();
    }

    public void subirNivel() {
        System.out.println("nivel subido");
        this.estadoTanque = this.estadoTanque.getSiguienteEstado();
        this.cargarSprites();
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

}
