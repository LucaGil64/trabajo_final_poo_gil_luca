package modelo.obstaculo;

import controlador.ControladorSprites;

public class Arbusto extends Obstaculo{

    public Arbusto(int posX, int posY) {
        super(posX, posY, 2, 8, 8, ControladorSprites.getSprite(8, 216, 8, 8), true, false, false);
    }

    @Override
    public boolean esSolido() {
        return false;
    }

}
