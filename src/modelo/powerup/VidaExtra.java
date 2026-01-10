package modelo.powerup;

import java.awt.image.BufferedImage;
import modelo.entidad.jugador.TanqueJugador;

public class VidaExtra extends PowerUp{

    public VidaExtra(int posX, int posY, int posZ, int ancho, int alto, BufferedImage sprite, int puntosAlActivar) {
        super(posX, posY, posZ, ancho, alto, sprite, puntosAlActivar);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {

    }

}
