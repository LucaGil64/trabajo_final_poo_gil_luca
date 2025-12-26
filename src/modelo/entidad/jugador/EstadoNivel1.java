package modelo.entidad.jugador;

import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilBasico;

public class EstadoNivel1 implements EstadoTanque{

    public int[] getSpritesTanque() {
        int[] coordenadas = {0,0, 64,0, 32,0, 96,0};
        return coordenadas;
    }

    public Proyectil crearProyectil(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilBasico(x, y, direccion, dueño);
    }

    public double getVelocidadMovimiento(){
        return 0.3;
    }

    public long getCooldownDisparo(){
        return 800;
    }

    public EstadoTanque getSiguienteEstado(){
        return new EstadoNivel2();
    }


}
