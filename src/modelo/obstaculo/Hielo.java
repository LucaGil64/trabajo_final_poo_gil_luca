package modelo.obstaculo;

import controlador.ControladorSprites;

public class Hielo extends Obstaculo{

    public Hielo(int posX, int posY) {
        super(posX, posY, 0, 8, 8, ControladorSprites.getSprite(8, 208, 8, 8), false, false, false);
    }

    @Override
    public boolean esSolido() {
        return false;
    }

}
