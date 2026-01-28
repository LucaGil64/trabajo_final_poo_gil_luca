package modelo.powerup;

import controlador.ControladorSprites;
import modelo.EstadoJuego;
import modelo.entidad.jugador.TanqueJugador;

public class VidaExtra extends PowerUp{

    public VidaExtra(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(80, 192, 16, 16), 600);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        EstadoJuego.getInstance().ganarVida();
    }

}
