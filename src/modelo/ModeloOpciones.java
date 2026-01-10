package modelo;

import controlador.ControladorSonido;


public class ModeloOpciones {

    private static ModeloOpciones instance;
    
    private int volumen;
    private boolean botonWasdK, botonFlechasF;

    private ModeloOpciones() {
        this.volumen = 50;
        this.botonFlechasF = true;
        this.botonWasdK = false;
    }

    public static ModeloOpciones getInstance() {
        if (instance == null) {
            instance = new ModeloOpciones();
        }
        return instance;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
        ControladorSonido.setVolumen(volumen);
    }

    public int getVolumen() {
        return this.volumen;
    }

    public void setFlechasFSeleccionado() {
        this.botonFlechasF = true;
        this.botonWasdK = false;
    }

    public boolean getFlechasFSeleccionado() {
        return this.botonFlechasF;
    }

    public void setWasdKSeleccionado() {
        this.botonFlechasF = false;
        this.botonWasdK = true;
    }

    public boolean getWasdKSeleccionado() {
        return this.botonWasdK;
    }

}
