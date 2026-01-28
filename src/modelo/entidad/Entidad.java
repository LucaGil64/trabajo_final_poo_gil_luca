package modelo.entidad;

import controlador.ControladorSprites;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import modelo.Direccion;
import modelo.GameObject;
import modelo.Mapa;
import modelo.Movible;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilBasico;

public abstract class Entidad extends GameObject implements Movible {

    protected Direccion direccion;
    protected double velocidadMovimiento;
    protected int vida;
    protected long coolDownDisparo;
    protected long ultimoDisparoTime;
    protected boolean moviendo;
    protected Map<Direccion, BufferedImage> mapaSprites;

    protected double xPrecisa;
    protected double yPrecisa;

    public Entidad (int posX, int posY, int ancho, int alto, BufferedImage sprite, Direccion direccion, double velocidadMovimiento, int vida, long coolDownDisparo, long ultimoDisparoTime, boolean moviendo, Map<Direccion, BufferedImage> mapaSprites) {
        super(posX, posY, 1, ancho, alto, sprite);
        this.direccion = direccion;
        this.velocidadMovimiento = velocidadMovimiento;
        this.vida = vida;
        this.coolDownDisparo = coolDownDisparo;
        this.ultimoDisparoTime = ultimoDisparoTime;
        this.moviendo = moviendo;
        this.mapaSprites = new HashMap<>();

        this.xPrecisa = posX;
        this.yPrecisa = posY;
    }

    @Override
    public void recibirImpacto(Proyectil proyectil) {

        this.vida--;

        if (this.vida <= 0) {
            this.destruir();
        }

        proyectil.destruir();
        
    }

    @Override
    public void destruir() {
        Thread hiloAnimacion = new Thread(new HiloAnimacionGrande(this.posX, this.posY));
        hiloAnimacion.start();
 

        this.existe = false;
    }

    public void disparar() {

        if (!this.existe) return;

        ProyectilBasico proyectil;
        int spawnX = this.posX;
        int spawnY = this.posY;
        
        long tiempoActual = System.currentTimeMillis();

        if (tiempoActual - this.ultimoDisparoTime > getCooldownDisparo()) {

            switch (this.direccion) {
            case Direccion.ARRIBA:     spawnX += 6; spawnY += 0; break;
            case Direccion.ABAJO:      spawnX += 6; spawnY += 16; break;
            case Direccion.IZQUIERDA:  spawnX += 0; spawnY += 6; break;
            case Direccion.DERECHA:    spawnX += 16; spawnY += 6; break;
            }


            Mapa.getInstance().addObjeto(crearProyectilEspecifico(spawnX, spawnY, direccion, this));
            this.ultimoDisparoTime = System.currentTimeMillis();
        }

    }

    protected abstract Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño);
    protected abstract long getCooldownDisparo();

    @Override
    public void mover() {

        if (!this.existe) return;

        if (!moviendo) return;

        double intentoX = this.xPrecisa;
        double intentoY = this.yPrecisa;

        switch (this.direccion) {
            case ARRIBA:    intentoY -= getVelocidadMovimiento(); break;
            case ABAJO:     intentoY += getVelocidadMovimiento(); break;
            case IZQUIERDA: intentoX -= getVelocidadMovimiento(); break;
            case DERECHA:   intentoX += getVelocidadMovimiento(); break;
        }


        if (puedeMoverse(intentoX, intentoY)) {

            this.xPrecisa = intentoX;
            this.yPrecisa = intentoY;

            this.posX = (int) this.xPrecisa;
            this.posY = (int) this.yPrecisa;

        }       
    }

    protected abstract double getVelocidadMovimiento();
    protected abstract void cargarSprites();


    private boolean puedeMoverse(double intentoX, double intentoY) {

        Rectangle hitboxFutura = new Rectangle((int) intentoX + 1, (int) intentoY + 1, this.ancho - 2, this.alto - 2); // Para ser permisivo
        //Rectangle hitboxFutura = new Rectangle((int) intentoX, (int) intentoY, this.ancho, this.alto); // Para ser mas estricto

        for (GameObject obj : Mapa.getInstance().getObjetos()) {

            if (obj == this) continue; // Chequea si el del array es la misma entidad
            if (!obj.esSolido()) continue; // Si no es solido se continua (osea se puede mover)

            // TODO: Hacer la parte de los powerUp


            if (hitboxFutura.intersects(obj.getBounds())) {
                return false;
            }

        }

        return true;

    }


    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;

        switch (direccion) {
            case ARRIBA:    this.sprite = mapaSprites.get(Direccion.ARRIBA); break;
            case ABAJO:     this.sprite = mapaSprites.get(Direccion.ABAJO); break;
            case IZQUIERDA: this.sprite = mapaSprites.get(Direccion.IZQUIERDA); break;
            case DERECHA:   this.sprite = mapaSprites.get(Direccion.DERECHA); break;
        }
    }

    public void setMoviendo(boolean moviendo) {
        this.moviendo = moviendo;
    }

    public Map<Direccion, BufferedImage> asignarSprites(int arribaX, int arribaY, int abajoX, int abajoY, int izquierdaX, int izquierdaY, int derechaX, int derechaY) {
        Map<Direccion, BufferedImage> sprites = new HashMap<>();
        
        sprites.put(Direccion.ARRIBA,    ControladorSprites.getSprite(arribaX, arribaY, 16, 16));
        sprites.put(Direccion.ABAJO,     ControladorSprites.getSprite(abajoX, abajoY, 16, 16));
        sprites.put(Direccion.IZQUIERDA, ControladorSprites.getSprite(izquierdaX, izquierdaY, 16, 16));
        sprites.put(Direccion.DERECHA,   ControladorSprites.getSprite(derechaX, derechaY, 16, 16));

        return sprites;
    }
}
