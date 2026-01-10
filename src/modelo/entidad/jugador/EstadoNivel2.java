package modelo.entidad.jugador;

import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilBasico;

public class EstadoNivel2 implements EstadoTanque{

    public int[] getSpritesTanque() {
        int[] coordenadas = {0,16, 64,16, 32,16, 96,16};
        return coordenadas;
    }

    public Proyectil crearProyectil(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilBasico(x, y, direccion, dueño);
    }

    public double getVelocidadMovimiento(){
        return 0.25;
    }

    public long getCooldownDisparo(){
        return 600;
    }
    
    public EstadoTanque getSiguienteEstado(){
        return new EstadoNivel3();
    }


}