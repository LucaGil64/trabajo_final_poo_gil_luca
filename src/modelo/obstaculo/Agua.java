package modelo.obstaculo;

import controlador.ControladorSprites;

public class Agua extends Obstaculo{

    public Agua(int posX, int posY) {
        super(posX, posY, 0, 8, 8, ControladorSprites.getSprite(16, 208, 8, 8), true, false, false);
    }

    @Override
    public boolean esSolido() {
        return true;
    }

}
