package modelo.powerup;

import java.awt.image.BufferedImage;
import modelo.GameObject;
import modelo.entidad.jugador.TanqueJugador;
import modelo.proyectil.Proyectil;

public abstract class PowerUp extends GameObject{

    private int puntosAlActivar;
    private long TIEMPO_PARA_DESAPARECER = 10000;

    public PowerUp(int posX, int posY, int posZ, int ancho, int alto, BufferedImage sprite, int puntosAlActivar) {
        super(posX, posY, posZ, ancho, alto, sprite);
        this.puntosAlActivar = puntosAlActivar;
    }

    public abstract void activar(TanqueJugador tanqueJugador);

    @Override
    public void recibirImpacto(Proyectil proyectil) {
        
    }

    @Override
    public boolean esSolido() {
        return false;
    }

    

}
