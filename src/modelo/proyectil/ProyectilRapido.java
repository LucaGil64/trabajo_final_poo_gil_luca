package modelo.proyectil;

import modelo.Direccion;
import modelo.GameObject;

public class ProyectilRapido extends Proyectil{

    public ProyectilRapido(int posX, int posY, Direccion direccion, GameObject dueño) {
        
        super(posX, posY, getAncho(direccion), getAlto(direccion), getSprite(direccion), direccion, 1.0, dueño);
    
    }



}
