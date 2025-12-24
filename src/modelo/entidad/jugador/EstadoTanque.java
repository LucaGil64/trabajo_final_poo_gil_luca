package modelo.entidad.jugador;

import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;

public interface EstadoTanque {

    public int[] getSpritesTanque();
    public Proyectil crearProyectil(int x, int y, Direccion direccion, Entidad dueño);
    public double getVelocidadMovimiento();
    public long getCooldownDisparo();
    public EstadoTanque getSiguienteEstado();

}
