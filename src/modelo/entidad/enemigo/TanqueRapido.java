package modelo.entidad.enemigo;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilBasico;

public class TanqueRapido extends TanqueEnemigo{

    public TanqueRapido(int posX, int posY, double agresividadDisparos, boolean dropPowerUp) {
        super(posX, posY, ControladorSprites.getSprite(64, 80, 16, 16), 0.3, 1, 1000, 200, agresividadDisparos, dropPowerUp, null);




        this.cargarSprites();
    }


    @Override
    protected Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilBasico(x, y, direccion, dueño);
    }

    @Override
    protected void cargarSprites() {
        
        if (this.dropPowerUp) {

            int[] c = {0,144, 64,144, 32,144, 96,144};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        } else {

            int[] c = {0,80, 64,80, 32,80, 96,80};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        }

    }

}
