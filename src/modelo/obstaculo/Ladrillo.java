package modelo.obstaculo;

import controlador.ControladorSprites;

public class Ladrillo extends Obstaculo{

    public Ladrillo(int posX, int posY) {
        super(posX, posY, 1, 8, 8, ControladorSprites.getSprite(0, 208, 8, 8), true, true, true);
    }

    @Override
    public boolean esSolido() {
        return true;
    }

}
