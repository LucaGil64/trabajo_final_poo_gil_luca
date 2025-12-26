package modelo.obstaculo;

import controlador.ControladorSprites;

public class Base extends Obstaculo{

    public Base(int posX, int posY) {
        super(posX, posY, 1, 16, 16, ControladorSprites.getSprite(32, 208, 16, 16), true, true, true);
    }


    @Override
    public boolean esSolido() {
        return true;
    }

    @Override
    public void destruir() {
        this.sprite = ControladorSprites.getSprite(48, 208, 16, 16);
        // TODO: Aca termina el juego (despues ver como hacer)
    }

}
