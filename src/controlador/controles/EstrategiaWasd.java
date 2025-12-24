package controlador.controles;

import java.awt.event.KeyEvent;

public class EstrategiaWasd implements EstrategiaControl{

    @Override
    public int getTeclaArriba() {
        return KeyEvent.VK_W;
    };

    @Override
    public int getTeclaAbajo() {
        return KeyEvent.VK_S;
    };

    @Override
    public int getTeclaIzquierda() {
        return KeyEvent.VK_A;
    };

    @Override
    public int getTeclaDerecha() {
        return KeyEvent.VK_D;
    };

    @Override
    public int getTeclaDisparo() {
        return KeyEvent.VK_K;
    };


}
