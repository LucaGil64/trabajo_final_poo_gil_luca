package modelo;

public class EstadoJuego {

    // Singleton
    private static EstadoJuego instancia;

    public static EstadoJuego getInstance() {
        if (instancia == null) instancia = new EstadoJuego();

        return instancia;
    }


    private String nombreJugador;
    private int vidas;
    private int puntaje;
    private boolean jugando;

    private EstadoJuego() {
        resetearPartida();
    }

    public void resetearPartida() {
        this.vidas = 3;       
        this.puntaje = 0;     
        this.jugando = true;
    }

    public void restarVida() {
        this.vidas--;
        if (this.vidas < 0) this.vidas = 0;
    }

    public void ganarVida() {
        this.vidas++;
    }

    public boolean esGameOver() {
        return vidas <= 0;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }



    public String getNombreJugador() {
        return this.nombreJugador;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean getJugando() {
        return this.jugando;
    }
}
