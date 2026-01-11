package modelo.entidad.enemigo;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilRapido;

public class TanqueBlindado extends TanqueEnemigo{

    public TanqueBlindado(int posX, int posY, double agresividadDisparos, boolean dropPowerUp) {
        super(posX, posY, ControladorSprites.getSprite(64, 112, 16, 16), 0.20, 4, 800, 400, agresividadDisparos, dropPowerUp, null);




        this.cargarSprites();
    }


    @Override
    protected Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilRapido(x, y, direccion, dueño);
    }

    @Override
    protected void cargarSprites() {
        
        if (this.dropPowerUp) {

            int[] c = {0,176, 64,176, 32,176, 96,176};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        } else {

            int[] c = {0,112, 64,112, 32,112, 96,112};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        }

    }

}
