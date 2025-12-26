package modelo.entidad.jugador;

import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilRapido;

public class EstadoNivel3 implements EstadoTanque{

    public int[] getSpritesTanque() {
        int[] coordenadas = {0,32, 64,32, 32,32, 96,32};
        return coordenadas;
    }

    public Proyectil crearProyectil(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilRapido(x, y, direccion, dueño);
    }

    public double getVelocidadMovimiento(){
        return 0.4;
    }

    public long getCooldownDisparo(){
        return 600;
    }
    
    public EstadoTanque getSiguienteEstado(){
        return new EstadoNivel4();
    }


}