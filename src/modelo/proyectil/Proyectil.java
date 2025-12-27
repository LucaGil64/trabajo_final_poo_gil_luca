package modelo.proyectil;

import controlador.ControladorSprites;
import java.awt.image.BufferedImage;
import modelo.Direccion;
import modelo.GameObject;
import modelo.Movible;

public abstract class Proyectil extends GameObject implements Movible{

    protected GameObject dueño;

    protected Direccion direccion;
    protected double velocidadProyectil;

    protected double xPrecisa;
    protected double yPrecisa;

    public Proyectil(int posX, int posY, int ancho, int alto, BufferedImage sprite, Direccion direccion, double velocidadProyectil, GameObject dueño) {
        super(posX, posY, 1, ancho, alto, sprite);
        this.direccion = direccion;
        this.velocidadProyectil = velocidadProyectil;
        this.dueño = dueño;

        this.xPrecisa = posX;
        this.yPrecisa = posY;
    }

    public GameObject getDueño() {
        return this.dueño;
    }

    protected static int getAncho(Direccion direccion){
        if (direccion == direccion.ARRIBA || direccion == Direccion.ABAJO) {
            return 3;
        }
        else if (direccion == Direccion.IZQUIERDA || direccion == Direccion.DERECHA) {
            return 4;
        }
        return 0;
    }

    protected static int getAlto(Direccion direccion){
        if (direccion == direccion.ARRIBA || direccion == Direccion.ABAJO) {
            return 4;
        }
        else if (direccion == Direccion.IZQUIERDA || direccion == Direccion.DERECHA) {
            return 3;
        }
        return 0;
    }

    protected static BufferedImage getSprite(Direccion direccion) {

        switch (direccion) {
            case Direccion.ARRIBA:      return ControladorSprites.getSprite(116, 228, 3, 4);
            case Direccion.ABAJO:       return ControladorSprites.getSprite(121, 228, 3, 4);
            case Direccion.IZQUIERDA:   return ControladorSprites.getSprite(116, 234, 4, 3);
            case Direccion.DERECHA:     return ControladorSprites.getSprite(122, 234, 4, 3);

            default: return ControladorSprites.getSprite(116, 226, 3, 4);
        }

    }

    public void destruir() {
        System.out.println("Se destruyo el proyectil");
        this.existe = false;
    }

    @Override
    public void mover() {

        int nuevaX = this.posX;
        int nuevaY = this.posY;

        switch (this.direccion) {
            case ARRIBA:    this.yPrecisa -= this.velocidadProyectil; break;
            case ABAJO:     this.yPrecisa += this.velocidadProyectil; break;
            case IZQUIERDA: this.xPrecisa -= this.velocidadProyectil; break;
            case DERECHA:   this.xPrecisa += this.velocidadProyectil; break;
        }

        this.posX = (int) this.xPrecisa;
        this.posY = (int) this.yPrecisa;
    }

}
