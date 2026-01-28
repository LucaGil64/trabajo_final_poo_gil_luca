package modelo;

import controlador.ControladorSonido;
import java.util.ArrayList;
import modelo.entidad.Entidad;
import modelo.entidad.enemigo.TanqueEnemigo;
import modelo.nivel.GestorNiveles;
import modelo.obstaculo.Obstaculo;
import modelo.powerup.PowerUp;
import modelo.proyectil.Proyectil;
import vista.PanelJuego;

public class GameLoop implements Runnable {

    private boolean ejecutando = false;
    private Thread hiloPrincipal;
    private PanelJuego panelJuego;

    public GameLoop(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;

        // Carga de sonidos
        ControladorSonido.cargarSonido("explosion-chica", "src/resources/sonidos/Sonido_Destruccion_Chica.wav", 10);
        ControladorSonido.cargarSonido("explosion-grande", "src/resources/sonidos/Sonido_Destruccion_Grande.wav", 10);
    }

    public synchronized void iniciar() {
        if (ejecutando) return;
        ejecutando = true;
        hiloPrincipal = new Thread(this, "Hilo-Juego");
        hiloPrincipal.start();
    }

    public synchronized void detener() {
        ejecutando = false;
        try {
            if(hiloPrincipal != null) {
            hiloPrincipal.join();
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        while (ejecutando) {
            // 1. ACTUALIZAR LOGICA (Movimiento)    En teoria despues mover todo esto a Mapa
            actualizarMovible();
            actualizarImpactable();
            actualizarPowerUps();
            Mapa.getInstance().actualizarArrayList();

            // 1.5. SPAWNER de enemigos
            GestorNiveles.getInstance().actualizarSpawner();

            // 2. REDIBUJAR (Llama al paintComponent de ContenedorJuego)
            panelJuego.getContenedorJuego().repaint();
            panelJuego.actualizarLabels();

            try {
                Thread.sleep(4); // Le puse 4 pq a veces se ve trabado (lo de los FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void actualizarPowerUps() {
        // Para el powerUp construir
        Mapa.getInstance().actualizarConstruccion();

        for (GameObject obj : Mapa.getInstance().getObjetos()) {
            
            if (obj instanceof PowerUp) {
                // Para destruir cuando pasen mas de 10 segundos en el piso
                ((PowerUp) obj).vefiricarTiempoVida();

                // Colision jugador - powerUp
                if (obj.getBounds().intersects(Mapa.getInstance().getTanqueJugador().getBounds())) {
                    obj.destruir();
                    ((PowerUp) obj).activar(Mapa.getInstance().getTanqueJugador());
                    EstadoJuego.getInstance().sumarPuntos(((PowerUp) obj).getPuntosAlActivar());
                }
            }

        }
    }

    private void actualizarMovible() {
        // Recorremos todos los objetos del mapa que implementan Movible
        for (GameObject obj : Mapa.getInstance().getObjetos()) {

            if (obj instanceof Movible) {
                // CASO 1: Si es un Enemigo, dejamos que su algoritmo controle todo (movimiento y disparo)
                if (obj instanceof TanqueEnemigo) {
                    // Chequea que el powerUp de congelar enemigos no este activo
                    if (!Mapa.getInstance().estanEnemigosCongelados()) {
                        ((TanqueEnemigo) obj).algoritmoDisparo();
                        ((TanqueEnemigo) obj).algoritmoMovimiento(); // Este ya incluye el mover() adentro
                    }
                    
                } 
                // CASO 2: Si NO es enemigo (Jugador, Bala), usamos el movimiento estándar
                else {
                    ((Movible) obj).mover();
                }
            }
            
        }
    }



    private void actualizarImpactable() {

        ArrayList<GameObject> array = Mapa.getInstance().getObjetos();

        for (GameObject proyectil : array) {
            // Si el proyectil ya no existe desde el frame anterior se saltea
            // Pero si muere durante este bucle seguiremos chequeando sus choques finales (para poder colisionar 2 cosas a la vez)
            if (!proyectil.getExiste()) continue;
            
            if (proyectil instanceof Proyectil) {

                for (GameObject impactable : array) {

                    if (proyectil == impactable) continue; // Si es el mismo se ignora
                    if (!impactable.getExiste()) continue; // Si el impactable ya no existe lo ignoramos

                    if (impactable instanceof Entidad) {
                        if ( ((Proyectil) proyectil).getDueño() == impactable) continue; // Para que no se pegue a si mismo


                        if (impactable instanceof TanqueEnemigo && ((Proyectil) proyectil).getDueño() instanceof TanqueEnemigo) continue; // Para que no se maten entre si los enemigos
                    }
                    

                    if (impactable instanceof Obstaculo) {
                        if( !((Obstaculo) impactable).getBloqueaProyectil() ) continue; // Si no bloquea proyectil no impacta
                    }

                    if (proyectil.getBounds().intersects(impactable.getBounds())) {
                        impactable.recibirImpacto((Proyectil) proyectil);
                    }
                }
            }
        }

    }

}
