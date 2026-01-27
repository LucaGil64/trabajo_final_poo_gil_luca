package modelo.nivel;

import java.util.Queue;
import modelo.entidad.enemigo.TipoEnemigo;

public class DatosNivel {

    private char [][] matrizMapa;
    private Queue<TipoEnemigo> colaEnemigos;
    private long velocidadSpawnEnemigos;

    public DatosNivel(char[][] matrizMapa, Queue<TipoEnemigo> colaEnemigos, int velocidadSpawnEnemigos) {
        this.matrizMapa = matrizMapa;
        this.colaEnemigos = colaEnemigos;
        this.velocidadSpawnEnemigos = velocidadSpawnEnemigos;
    
    }

    public TipoEnemigo obtenerSiguienteEnemigo() {
        return this.colaEnemigos.poll();
    }

    public boolean quedanEnemigos() {
        return !(this.colaEnemigos.size() <= 0);
    }

    public int cuantosEnemigos() {
        return this.colaEnemigos.size();
    }

    public char [][] getMatrizMapa() {
        return this.matrizMapa;
    }

    public long getVelocidadSpawnEnemigos() {
        return this.velocidadSpawnEnemigos;
    }

}
