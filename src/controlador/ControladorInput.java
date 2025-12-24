package controlador;

import controlador.controles.EstrategiaControl;
import controlador.controles.EstrategiaFlechas;
import controlador.controles.EstrategiaWasd;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.Direccion;
import modelo.ModeloOpciones;
import modelo.entidad.jugador.TanqueJugador;

public class ControladorInput implements KeyListener {

    private boolean teclaArriba, teclaAbajo, teclaIzquierda, teclaDerecha;

    private TanqueJugador tanqueJugador;
    private static EstrategiaControl estrategiaControl;

    public ControladorInput(TanqueJugador tanqueJugador) {
        this.tanqueJugador = tanqueJugador;
        if (ModeloOpciones.getInstance().getFlechasFSeleccionado()) {
            ControladorInput.estrategiaControl = new EstrategiaFlechas();

        } else if (ModeloOpciones.getInstance().getWasdKSeleccionado()) {
            ControladorInput.estrategiaControl = new EstrategiaWasd();
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        // 1. Marcamos la tecla como presionada
        int codigo = e.getKeyCode();

        if (codigo == ControladorInput.estrategiaControl.getTeclaArriba()) {
            teclaArriba = true;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaAbajo()) {
            teclaAbajo = true;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaIzquierda()) {
            teclaIzquierda = true;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaDerecha()) {
            teclaDerecha = true;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaDisparo()) {
            this.tanqueJugador.disparar();
        }

        // 2. Actualizamos el tanque
        actualizarTanque();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 1. Marcamos la tecla como soltada
        int codigo = e.getKeyCode();

        if (codigo == ControladorInput.estrategiaControl.getTeclaArriba()) {
            teclaArriba = false;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaAbajo()) {
            teclaAbajo = false;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaIzquierda()) {
            teclaIzquierda = false;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaDerecha()) {
            teclaDerecha = false;
        }
        else if (codigo == ControladorInput.estrategiaControl.getTeclaDisparo()) {
            
        }
        // 2. Actualizamos el tanque
        actualizarTanque();
    }

    private void actualizarTanque() {
        // Si hay CUALQUIER tecla hundida, el tanque debe moverse
        tanqueJugador.setMoviendo(teclaArriba || teclaAbajo || teclaIzquierda || teclaDerecha);

        // Definimos la dirección (podes dar prioridades)
        if (teclaArriba) tanqueJugador.setDireccion(Direccion.ARRIBA);
        else if (teclaAbajo) tanqueJugador.setDireccion(Direccion.ABAJO);
        else if (teclaIzquierda) tanqueJugador.setDireccion(Direccion.IZQUIERDA);
        else if (teclaDerecha) tanqueJugador.setDireccion(Direccion.DERECHA);
    }


    public static void setEstrategiaFlechas() {
        ControladorInput.estrategiaControl = new EstrategiaFlechas();
    }

    public static void setEstrategiaWasd() {
        ControladorInput.estrategiaControl = new EstrategiaWasd();
    }
    // @Override
    // public void keyPressed(KeyEvent e) {
    //     int codigo = e.getKeyCode();

    //     // Al presionar, cambiamos la dirección y activamos el movimiento
    //     switch (codigo) {
    //         case KeyEvent.VK_UP:
    //             tanqueJugador.setDireccion(Direccion.ARRIBA);
    //             tanqueJugador.setMoviendo(true);
    //             break;
    //         case KeyEvent.VK_DOWN:
    //             tanqueJugador.setDireccion(Direccion.ABAJO);
    //             tanqueJugador.setMoviendo(true);
    //             break;
    //         case KeyEvent.VK_LEFT:
    //             tanqueJugador.setDireccion(Direccion.IZQUIERDA);
    //             tanqueJugador.setMoviendo(true);
    //             break;
    //         case KeyEvent.VK_RIGHT:
    //             tanqueJugador.setDireccion(Direccion.DERECHA);
    //             tanqueJugador.setMoviendo(true);
    //             break;
    //         case KeyEvent.VK_SPACE:
    //             // Aquí podrías llamar al método disparar cuando lo tengas
    //             // tanqueJugador.disparar();
    //             break;
    //     }
    // }

    // @Override
    // public void keyReleased(KeyEvent e) {
    //     int codigo = e.getKeyCode();
    //     Direccion direccionActual = tanqueJugador.getDireccion();

    //     boolean esTeclaArriba = (codigo == KeyEvent.VK_UP && direccionActual == Direccion.ARRIBA);
    //     boolean esTeclaAbajo = (codigo == KeyEvent.VK_DOWN && direccionActual == Direccion.ABAJO);
    //     boolean esTeclaIzquierda = (codigo == KeyEvent.VK_LEFT && direccionActual == Direccion.IZQUIERDA);
    //     boolean esTeclaDerecha = (codigo == KeyEvent.VK_RIGHT && direccionActual == Direccion.DERECHA);

    //     if (esTeclaArriba || esTeclaAbajo || esTeclaIzquierda || esTeclaDerecha) {
    //         tanqueJugador.setMoviendo(false);
    //     }
    // }

    @Override
    public void keyTyped(KeyEvent e) {} // Esto se usa
}

