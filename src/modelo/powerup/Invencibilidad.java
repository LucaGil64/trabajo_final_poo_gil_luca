package modelo.powerup;

import controlador.ControladorSprites;
import modelo.entidad.jugador.TanqueJugador;

public class Invencibilidad extends PowerUp{

    public Invencibilidad(int posX, int posY) {
        super(posX, posY, ControladorSprites.getSprite(0, 192, 16, 16), 400);
    }

    @Override
    public void activar(TanqueJugador tanqueJugador) {

        // Por si lo agarra 2 veces, para que no se vea 2 veces el escudo
        if (!tanqueJugador.esInvencible()) {
            Thread hiloAnimacion = new Thread(new HiloAnimacionInvencibilidad(tanqueJugador));
            hiloAnimacion.start();
        }

        // Pero que siempre que se vuelve a agarrar actualiza los 10 segundos de powerUp
        tanqueJugador.activarInvencibilidad(10000);
 
    }

}
