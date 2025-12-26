package modelo.obstaculo;

import java.awt.image.BufferedImage;
import modelo.GameObject;
import modelo.proyectil.Proyectil;

public abstract class Obstaculo extends GameObject{

    protected boolean bloqueaEntidad;
    protected boolean bloqueaProyectil;
    protected boolean destructible;

    public Obstaculo (int posX, int posY, int posZ, int ancho, int alto, BufferedImage sprite, boolean bloqueaEntidad, boolean bloqueaProyectil, boolean destructible) {
        super(posX, posY, posZ, ancho, alto, sprite);
        this.bloqueaEntidad = bloqueaEntidad;
        this.bloqueaProyectil = bloqueaProyectil;
        this.destructible = destructible;
    }

    public boolean getBloqueaProyectil() {
        return this.bloqueaProyectil;
    }

    @Override
    public void recibirImpacto(Proyectil proyectil) {
        proyectil.destruir();

        if (this.destructible) {
            this.destruir();
        }
    }

}
