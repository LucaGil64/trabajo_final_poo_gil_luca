package modelo.entidad.enemigo;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import modelo.Direccion;
import modelo.EstadoJuego;
import modelo.entidad.Entidad;
import modelo.entidad.HiloAnimacionGrande;
import modelo.proyectil.Proyectil;

public abstract class TanqueEnemigo extends Entidad{

    protected int puntosAlMorir;
    protected double agresividadDisparos;
    protected boolean dropPowerUp;

    protected Random random;
    protected int pasosRestantes = 0;
    

    public TanqueEnemigo (int posX, int posY, BufferedImage sprite, double velocidadMovimiento, int vida, long coolDownDisparo, int puntosAlMorir, double agresividadDisparos, boolean dropPowerUp, Map<Direccion, BufferedImage> mapaSprites) {
        
        super(posX, posY, 16, 16, sprite, Direccion.ABAJO, velocidadMovimiento, vida, coolDownDisparo, 0, false, mapaSprites);

        this.puntosAlMorir = puntosAlMorir;
        this.agresividadDisparos = agresividadDisparos;
        this.dropPowerUp = dropPowerUp;
        this.mapaSprites = new HashMap<>();


        this.random = new Random();

    }


    @Override
    protected abstract Proyectil crearProyectilEspecifico(int x, int y, Direccion direccion, Entidad dueño);

    @Override
    protected abstract void cargarSprites();

    @Override
    protected long getCooldownDisparo() {
        return this.coolDownDisparo;
    }

    @Override
    protected double getVelocidadMovimiento() {
        return this.velocidadMovimiento;
    }

    @Override
    public void destruir() {
        Thread hiloAnimacion = new Thread(new HiloAnimacionGrande(this.posX, this.posY));
        hiloAnimacion.start();
 

        System.out.println("Se destruyo la entidad");
        this.existe = false;

        EstadoJuego.getInstance().sumarPuntos(this.puntosAlMorir);
    }



    public void algoritmoMovimiento() {

        if (this.pasosRestantes <= 0) {
            cambiarDireccionRandom();
            this.pasosRestantes = random.nextInt(500) + 500;
        }

        double xAntes = this.xPrecisa;
        double yAntes = this.yPrecisa;

        this.moviendo = true;
        this.mover();


        if (Math.abs(xPrecisa - xAntes) < 0.01 && Math.abs(yPrecisa - yAntes) < 0.01 && pasosRestantes < 800) {
            cambiarDireccionRandom();
            this.pasosRestantes = 0;
        }


        this.pasosRestantes--;

    }

    private void cambiarDireccionRandom() {
        int dir = random.nextInt(4);

        switch (dir) {
            case 0: this.setDireccion(Direccion.ARRIBA); break;
            case 1: this.setDireccion(Direccion.ABAJO); break;
            case 2: this.setDireccion(Direccion.IZQUIERDA); break;
            case 3: this.setDireccion(Direccion.DERECHA); break;
            //case 4: this.setDireccion(Direccion.ABAJO); break; // 2 abajo para que tenga mas probabilidad
            default:
                throw new AssertionError();
        }
    }

    public void algoritmoDisparo() {

        // Random random = new Random();
        // float randomFloat = random.nextFloat();

        // if (this.agresividadDisparos > randomFloat) this.disparar();

        this.disparar();
    }

}
