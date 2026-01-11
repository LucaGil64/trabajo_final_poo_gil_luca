package modelo.entidad.enemigo;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilRapido;

public class TanqueAltaCadencia extends TanqueEnemigo{

    public TanqueAltaCadencia(int posX, int posY, double agresividadDisparos, boolean dropPowerUp) {
        super(posX, posY, ControladorSprites.getSprite(64, 96, 16, 16), 0.20, 1, 600, 300, agresividadDisparos, dropPowerUp, null);




        this.cargarSprites();
    }


    @Override
    protected Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilRapido(x, y, direccion, dueño);
    }

    @Override
    protected void cargarSprites() {
        
        if (this.dropPowerUp) {

            int[] c = {0,160, 64,160, 32,160, 96,160};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        } else {

            int[] c = {0,96, 64,96, 32,96, 96,96};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        }

    }

}
