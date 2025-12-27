package modelo;

import java.util.ArrayList;
import modelo.entidad.Entidad;
import modelo.obstaculo.Obstaculo;
import modelo.proyectil.Proyectil;
import vista.PanelJuego;

public class GameLoop implements Runnable {

    private boolean ejecutando = false;
    private Thread hiloPrincipal;
    private PanelJuego panelJuego;

    public GameLoop(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
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
            // 1. ACTUALIZAR LÓGICA (Movimiento)    En teoria despues mover todo esto a Mapa
            actualizarMovible();
            actualizarImpactable();
            Mapa.getInstance().actualizarArrayList();

            // 2. REDIBUJAR (Llama al paintComponent de ContenedorJuego)
            panelJuego.getContenedorJuego().repaint();

            // 3. REGULAR VELOCIDAD (60 FPS)
            try {
                Thread.sleep(4); // Le puse 4 pq a veces se ve trabado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizarMovible() {
        // Recorremos todos los objetos del mapa que implementan Movible
        for (GameObject obj : Mapa.getInstance().getObjetos()) {
            if (obj instanceof Movible) {
                ((Movible) obj).mover();
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

                    // TODO: Chequear para q no pueda colisionar el proyectil de un tanque contra sigo mismo y que los enemigos no se maten entre si
                    if (impactable instanceof Entidad) {
                        if ( ((Proyectil) proyectil).getDueño() == impactable) {
                            continue;
                        }
                    }
                    

                    if (impactable instanceof Obstaculo) {
                        if( !((Obstaculo) impactable).getBloqueaProyectil() ) continue;
                    }

                    if (proyectil.getBounds().intersects(impactable.getBounds())) {
                        System.out.println("Colision de proyectil");
                        impactable.recibirImpacto((Proyectil) proyectil);
                    }
                }
            }
        }

    }

}
