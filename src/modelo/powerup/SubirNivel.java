package modelo.powerup;

import java.awt.image.BufferedImage;
import modelo.entidad.jugador.TanqueJugador;

public class SubirNivel extends PowerUp{

    public SubirNivel(int posX, int posY, int posZ, int ancho, int alto, BufferedImage sprite, int puntosAlActivar) {
        super(posX, posY, posZ, ancho, alto, sprite, 500);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        tanqueJugador.subirNivel();
    }

}
