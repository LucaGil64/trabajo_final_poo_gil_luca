package modelo.nivel;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import modelo.GameObject;
import modelo.Mapa;
import modelo.entidad.enemigo.EnemigoFactory;
import modelo.entidad.enemigo.TanqueEnemigo;
import modelo.entidad.enemigo.TipoEnemigo;

public class GestorNiveles {

    // Singletonn
    private static GestorNiveles instancia;

    public static GestorNiveles getInstance() {
        if (instancia == null) instancia = new GestorNiveles();
        return instancia;
    }


    private ArrayList<DatosNivel> listaNiveles;
    private int nivelActual;
    private boolean juegoTerminado;

    private long ultimoTiempoSpawn;
    private final int MAX_ENEMIGOS_SIMULTANEOS = 5;
    private Random random = new Random();

    private boolean esperandoTransicion = false;


    private GestorNiveles() {
        resetearNiveles();
    }

    public void resetearNiveles() {
        this.listaNiveles = new ArrayList<>();
        this.nivelActual = 1;
        this.juegoTerminado = false;

        this.ultimoTiempoSpawn = 0;
        this.esperandoTransicion = false;

        inicializarNiveles();
    }


    private void inicializarNiveles() {

        // CONFIGURACION NIVEL 1
        Queue<TipoEnemigo> colaNivel1 = new LinkedList<TipoEnemigo>();
        // for (int i = 0; i < 16; i++) colaNivel1.offer(TipoEnemigo.BASICO);
        for (int i = 0; i < 1; i++) colaNivel1.offer(TipoEnemigo.RAPIDO);
        DatosNivel nivel1 = new DatosNivel(Niveles.NIVEL_1, colaNivel1, 4000);

        this.listaNiveles.add(nivel1);


        // CONFIGURACION NIVEL 2
        Queue<TipoEnemigo> colaNivel2 = new LinkedList<TipoEnemigo>();
        for (int i = 0; i < 5; i++) colaNivel2.offer(TipoEnemigo.BASICO);
        // for (int i = 0; i < 5; i++) colaNivel2.offer(TipoEnemigo.RAPIDO);
        // for (int i = 0; i < 5; i++) colaNivel2.offer(TipoEnemigo.ALTA_CADENCIA);
        // for (int i = 0; i < 5; i++) colaNivel2.offer(TipoEnemigo.BLINDADO);
        DatosNivel nivel2 = new DatosNivel(Niveles.NIVEL_2, colaNivel2, 4000);

        this.listaNiveles.add(nivel2);


        // CONFIGURACION NIVEL 3
        Queue<TipoEnemigo> colaNivel3 = new LinkedList<TipoEnemigo>();
        for (int i = 0; i < 2; i++) colaNivel3.offer(TipoEnemigo.RAPIDO);
        // for (int i = 0; i < 7; i++) colaNivel3.offer(TipoEnemigo.ALTA_CADENCIA);
        // for (int i = 0; i < 6; i++) colaNivel3.offer(TipoEnemigo.BLINDADO);
        DatosNivel nivel3 = new DatosNivel(Niveles.NIVEL_3, colaNivel3, 4000);

        this.listaNiveles.add(nivel3);

    }



    public void cargarNivelActual() {
        this.ultimoTiempoSpawn = 0; // Para que siempre aparezca rapido al principio

        if (this.nivelActual - 1 >= this.listaNiveles.size()) {
            this.nivelActual--; // Para que no se vea mal en el label
            System.out.println("JUEGO TERMINADO, GANASTE!!");
            juegoTerminado = true;
            return;
        }

        System.out.println("--- CARGANDO NIVEL " + (this.nivelActual) + " ---");

        
        int indiceNivelActual = this.nivelActual - 1;
        DatosNivel datos = this.listaNiveles.get(indiceNivelActual);

        Mapa.getInstance().limpiarMapa();
        Mapa.getInstance().cargarNivel(datos.getMatrizMapa());
        Mapa.getInstance().respawnJugador(false);
    }



    public void actualizarSpawner() {
        if (juegoTerminado) return;

        DatosNivel datos = this.listaNiveles.get(this.nivelActual - 1);
        long tiempoActual = System.currentTimeMillis();

        // Ya no quedan enemigos por spawnear ni en el mapa
        if (!datos.quedanEnemigos() && Mapa.getInstance().getCantidadEnemigosVivos() <= 0) {

            if (!esperandoTransicion) {
                // Esto se ejecuta solo una vez cuando muere el ultimo enemigo
                this.esperandoTransicion = true;
                this.ultimoTiempoSpawn = tiempoActual;
            
            } else {
                // Chequeamos si ya pasaron los 4 seg desde que murio el ultimo enemigo
                if (tiempoActual - this.ultimoTiempoSpawn > datos.getVelocidadSpawnEnemigos()) {
                    this.esperandoTransicion = false;
                    this.nivelActual++;
                    this.cargarNivelActual();
                }
            }

        } else {
            this.esperandoTransicion = false; 

            if (tiempoActual - this.ultimoTiempoSpawn > datos.getVelocidadSpawnEnemigos()) {

                if (datos.quedanEnemigos() && Mapa.getInstance().getCantidadEnemigosVivos() < MAX_ENEMIGOS_SIMULTANEOS) {
                    
                    generarEnemigo(datos);
                    this.ultimoTiempoSpawn = tiempoActual;
                }
            }
        }
    }

    private void generarEnemigo(DatosNivel datos) {

        int xValida = buscarPosicionXLibre();

        if (xValida == -1) {
            System.out.println("No se encontro un lugar para spawnear el enemigo");
            return;
        }

        TipoEnemigo tipo = datos.obtenerSiguienteEnemigo();
        TanqueEnemigo nuevoEnemigo = EnemigoFactory.crearEnemigo(tipo, xValida, 8, false);
        Mapa.getInstance().addObjeto(nuevoEnemigo);
    }

    private int buscarPosicionXLibre() {
        
        int intentos = 0;

        // Solo se le permite 10 intentos, pq si no encuentra lugar se traba en ese frame el juego
        while (intentos < 10) {

            int xAleatoria = random.nextInt(193) + 8; // Numero random entre 8 y 200
            Rectangle areaFutura = new Rectangle(xAleatoria, 8, 16, 16);

            if (!colisionaConAlgo(areaFutura)) {
                return xAleatoria;
            }
            intentos++;
        }

        return -1;
    }

    private boolean colisionaConAlgo(Rectangle areaFutura) {
        
        for (GameObject obj : Mapa.getInstance().getObjetos()) {
            
            if (obj.getExiste() && areaFutura.intersects(obj.getBounds())) return true; // Intersecta con algo

        }

        return false; // No intersecta con nada

    }




    public int getNivelActual() {
        return this.nivelActual;
    }

    public DatosNivel getDatosNivelActual() {
        return this.listaNiveles.get(this.nivelActual - 1);
    }
}
