package controlador.controles;

import java.awt.event.KeyEvent;

public class EstrategiaFlechas implements EstrategiaControl{

    @Override
    public int getTeclaArriba() {
        return KeyEvent.VK_UP;
    };

    @Override
    public int getTeclaAbajo() {
        return KeyEvent.VK_DOWN;
    };

    @Override
    public int getTeclaIzquierda() {
        return KeyEvent.VK_LEFT;
    };

    @Override
    public int getTeclaDerecha() {
        return KeyEvent.VK_RIGHT;
    };

    @Override
    public int getTeclaDisparo() {
        return KeyEvent.VK_F;
    };


}
