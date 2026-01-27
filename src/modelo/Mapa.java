package modelo;

import java.awt.Graphics;
import java.util.ArrayList;
import modelo.entidad.enemigo.TanqueEnemigo;
import modelo.entidad.jugador.TanqueJugador;
import modelo.obstaculo.*;

public class Mapa {
    
    // SINGLETON
    private static Mapa instance;
    private ArrayList<GameObject> objetos;

    private TanqueJugador tanqueJugador;
    
    // Lista de espera para evitar choques de hilos al agregar cosas (proyectiles)
    private ArrayList<GameObject> objetosPendientes; 
    
    private final int TILE_SIZE = 8;

    private Mapa() {
        objetos = new ArrayList<>();
        objetosPendientes = new ArrayList<>(); // Inicializamos la lista de espera

        this.tanqueJugador = new TanqueJugador(80, 200);
        this.objetos.add(tanqueJugador);
    }

    public static Mapa getInstance() {
        if (instance == null) {
            instance = new Mapa();
        }
        return instance;
    }

    public void cargarNivel(char[][] nivel) {
        this.limpiarMapa();
        
        for (int fila = 0; fila < nivel.length; fila++) {
            for (int col = 0; col < nivel[fila].length; col++) {
                int x = col * TILE_SIZE;
                int y = fila * TILE_SIZE;
                char tipo = nivel[fila][col];

                // ' ' = Nada, L = Ladrillo, A = Acero, B = Arbusto, W = Agua, H = Hielo, S = Base
                // P = Spawn Jugador, E = Spawn Enemigo

                switch (tipo) {
                    case 'L': objetos.add(new Ladrillo(x, y)); break;
                    case 'A': objetos.add(new Acero(x, y)); break;
                    case 'B': objetos.add(new Arbusto(x, y)); break;
                    case 'W': objetos.add(new Agua(x, y)); break;
                    case 'H': objetos.add(new Hielo(x, y)); break;
                    case 'S': objetos.add(new Base(x, y)); break;
                    
                    case 'P': // Invocar lógica de spawn jugador
                        break;
                    case 'E': // Invocar EnemigoFactory
                        break;
                }
            }
        }
    }

    public void actualizarArrayList() {
        
        // Bloqueamos la lista un momento para modificarla de forma segura
        synchronized (this) {
            
            // 1. Borrar muertos
            // Esto elimina todos los objetos donde getExiste() sea false de forma segura
            this.objetos.removeIf(obj -> !obj.getExiste());

            // 2. Agregar los nuevos (que estaban en la sala de espera)
            if (!objetosPendientes.isEmpty()) {
                this.objetos.addAll(objetosPendientes);
                objetosPendientes.clear();
            }
        }
    }

    public void dibujarTodo(Graphics g) {

        ArrayList<GameObject> copiaSegura;

        // "Congelamos" un instante para sacar una foto (copia) de la lista actual.
        // Esto evita el error ConcurrentModificationException al pintar.
        synchronized (this) {
            copiaSegura = new ArrayList<>(this.objetos);
        }

        // Iteramos sobre la COPIA, no sobre la original
        for (GameObject obj: copiaSegura) {
            if(obj.posZ == 0) obj.dibujar(g);
        }

        for (GameObject obj: copiaSegura) {
            if(obj.posZ == 1) obj.dibujar(g);
        }

        for (GameObject obj: copiaSegura) {
            if(obj.posZ == 2) obj.dibujar(g);
        }
    }

    // Usamos synchronized para que agregar desde el teclado sea seguro
    public synchronized void addObjeto(GameObject obj) {
        // Ahora agregamos a la lista de espera, no a la principal directamente
        this.objetosPendientes.add(obj);
    }

    public void limpiarMapa() {
        this.objetos.clear();
        this.objetosPendientes.clear();

        if (this.tanqueJugador != null) this.objetos.add(this.tanqueJugador);
    }

    public void respawnJugador(boolean murio) {

        if (murio) this.tanqueJugador.resetearTotal();
        else this.tanqueJugador.resetearPosicion();

    }   


    public int getCantidadEnemigosVivos() {
        
        int contador = 0;
        for (GameObject obj : this.objetos) {
            if (obj instanceof TanqueEnemigo && obj.getExiste()) {
                contador++;
            }
        }
        return contador;

    }



    public ArrayList<GameObject> getObjetos() {
        return this.objetos;
    }

    public TanqueJugador getTanqueJugador() {
        return this.tanqueJugador;
    }

}