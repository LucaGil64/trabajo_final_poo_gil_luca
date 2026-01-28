package modelo.powerup;

import controlador.ControladorSprites;
import modelo.Mapa;
import modelo.entidad.jugador.TanqueJugador;

public class Congelar extends PowerUp{

    public Congelar(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(16, 192, 16, 16), 500);
    }
    @Override
    public void activar(TanqueJugador tanqueJugador) {
        Mapa.getInstance().activarCongelamiento(10000);
    }

}
