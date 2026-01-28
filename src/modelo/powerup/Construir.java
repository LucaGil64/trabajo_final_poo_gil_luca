package modelo.powerup;

import controlador.ControladorSprites;
import modelo.Mapa;
import modelo.entidad.jugador.TanqueJugador;

public class Construir extends PowerUp{

    public Construir(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(32, 192, 16, 16), 400);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {
        Mapa.getInstance().destruirObstaculoBase();
        Mapa.getInstance().construirAceroBase();
        Mapa.getInstance().activarConstruir(10000);
    }



}
