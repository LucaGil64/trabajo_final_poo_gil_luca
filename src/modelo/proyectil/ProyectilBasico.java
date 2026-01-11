package modelo.proyectil;

import modelo.Direccion;
import modelo.GameObject;

public class ProyectilBasico extends Proyectil{

    public ProyectilBasico(int posX, int posY, Direccion direccion, GameObject dueño) {
        
        super(posX, posY, getAncho(direccion), getAlto(direccion), getSprite(direccion), direccion, 0.6, dueño);
    
    }



}
