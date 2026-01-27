package modelo;

public class EstadoJuego {

    // Singleton
    private static EstadoJuego instancia;

    public static EstadoJuego getInstance() {
        if (instancia == null) instancia = new EstadoJuego();

        return instancia;
    }


    private int vidas;
    private int puntaje;

    private EstadoJuego() {
        resetearPartida();
    }

    public void resetearPartida() {
        this.vidas = 3;       
        this.puntaje = 0;     
    }

    public void restarVida() {
        this.vidas--;
        if (this.vidas < 0) this.vidas = 0;
    }

    public void ganarVida() {
        this.vidas++;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean esGameOver() {
        return vidas <= 0;
    }


    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public int getPuntaje() {
        return puntaje;
    }
}
