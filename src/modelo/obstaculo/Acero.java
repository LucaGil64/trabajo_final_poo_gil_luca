package modelo.obstaculo;

import controlador.ControladorSprites;

public class Acero extends Obstaculo{

    public Acero(int posX, int posY) {
        super(posX, posY, 1, 8, 8, ControladorSprites.getSprite(0, 216, 8, 8), true, true, false);
    }

    @Override
    public boolean esSolido() {
        return true;
    }

}
