package modelo.entidad.jugador;

import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilRapido;

public class EstadoNivel4 implements EstadoTanque{

    public int[] getSpritesTanque() {
        int[] coordenadas = {0,48, 64,48, 32,48, 96,48};
        return coordenadas;
    }

    public Proyectil crearProyectil(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilRapido(x, y, direccion, dueño);
    }

    public double getVelocidadMovimiento(){
        return 0.45;
    }

    public long getCooldownDisparo(){
        return 400;
    }
    
    public EstadoTanque getSiguienteEstado(){
        return this;
    }


}