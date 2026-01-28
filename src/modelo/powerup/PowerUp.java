package modelo.powerup;

import java.awt.image.BufferedImage;
import modelo.GameObject;
import modelo.entidad.jugador.TanqueJugador;
import modelo.proyectil.Proyectil;

public abstract class PowerUp extends GameObject {

    private int puntosAlActivar;
    private long tiempoCreacion;
    private final long DURACION_EN_PISO = 10000;

    public PowerUp(int posX, int posY, BufferedImage sprite, int puntosAlActivar) {
        super(posX, posY, 3, 16, 16, sprite);
        this.puntosAlActivar = puntosAlActivar;
        this.tiempoCreacion = System.currentTimeMillis();
    }

    public abstract void activar(TanqueJugador tanqueJugador);

    @Override
    public void recibirImpacto(Proyectil proyectil) {
        
    }

    @Override
    public boolean esSolido() {
        return false;
    }

    public void vefiricarTiempoVida() {
        if (System.currentTimeMillis() - this.tiempoCreacion > this.DURACION_EN_PISO){
            this.destruir();
        }
    }

    public int getPuntosAlActivar() {
        return this.puntosAlActivar;
    }

}
