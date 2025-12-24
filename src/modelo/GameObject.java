package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import modelo.proyectil.Proyectil;

public abstract class GameObject implements Impactable{

    protected int posX;
    protected int posY;
    protected int posZ;
    protected int ancho;
    protected int alto;
    protected boolean existe;
    protected BufferedImage sprite;

    public GameObject(int posX, int posY, int posZ, int ancho, int alto, BufferedImage sprite) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.ancho = ancho;
        this.alto = alto;
        this.existe = true;
        this.sprite = sprite;
    }

    public void dibujar(Graphics g) {
        if (this.sprite != null) {
            g.drawImage(sprite, posX, posY, null);
        }
    }

    
    @Override
    public void recibirImpacto(Proyectil proyectil) {
        proyectil.destruir(); // luego se sobre-escribe, no todos los obstaculos lo destruyen (ej: agua)
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, this.ancho, this.alto);
    }
    
    @Override
    public boolean esSolido() {
        return true;
    }

    public void destruir() {
        this.existe = false;
    }


    public boolean getExiste() {
        return this.existe;
    }

}
