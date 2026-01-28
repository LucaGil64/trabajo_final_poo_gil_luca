package modelo.powerup;

import controlador.ControladorSprites;
import modelo.entidad.jugador.TanqueJugador;

public class SubirNivel extends PowerUp{

    public SubirNivel(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(48, 192, 16, 16), 500);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        tanqueJugador.subirNivel();
    }

}
