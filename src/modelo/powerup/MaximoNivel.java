package modelo.powerup;

import controlador.ControladorSprites;
import modelo.entidad.jugador.TanqueJugador;

public class MaximoNivel extends PowerUp{

    public MaximoNivel(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(96, 192, 16, 16), 800);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        tanqueJugador.subirNivel();
        tanqueJugador.subirNivel();
        tanqueJugador.subirNivel();
    }

}
