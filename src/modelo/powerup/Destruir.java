package modelo.powerup;

import controlador.ControladorSprites;
import modelo.Mapa;
import modelo.entidad.jugador.TanqueJugador;

public class Destruir extends PowerUp{

    public Destruir(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(64, 192, 16, 16), 500);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        Mapa.getInstance().destruirTanquesEnemigosVivos();
    }

}
