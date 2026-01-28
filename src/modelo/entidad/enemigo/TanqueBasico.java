package modelo.entidad.enemigo;

import controlador.ControladorSprites;
import modelo.Direccion;
import modelo.entidad.Entidad;
import modelo.proyectil.Proyectil;
import modelo.proyectil.ProyectilBasico;

public class TanqueBasico extends TanqueEnemigo{

    public TanqueBasico(int posX, int posY, double agresividadDisparos, boolean dropPowerUp) {
        super(posX, posY, ControladorSprites.getSprite(64, 64, 16, 16), 0.15, 1, 1200, 100, agresividadDisparos, dropPowerUp, null);




        this.cargarSprites();
        setDireccion(this.direccion);
    }


    @Override
    protected Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño) {
        return new ProyectilBasico(x, y, direccion, dueño);
    }

    @Override
    protected void cargarSprites() {

        if (this.dropPowerUp) {

            int[] c = {0,128, 64,128, 32,128, 96,128};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        } else {

            int[] c = {0,64, 64,64, 32,64, 96,64};
            this.mapaSprites = this.asignarSprites(c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]);

        }

    }

}
