package modelo.powerup;

import java.util.Random;



public class PowerUpFactory {

    private static final int PROB_INVENCIBILIDAD = 20;
    private static final int PROB_CONSTRUIR = 20;
    private static final int PROB_CONGELAR = 15;
    private static final int PROB_SUBIRNIVEL = 15;
    private static final int PROB_DESTRUIR = 15;
    private static final int PROB_VIDAEXTRA = 10;
    private static final int PROB_MAXIMONIVEL = 5;

    private static Random random = new Random();

    public static PowerUp crearPowerUp() {

        int chance = random.nextInt(100);

        int x = random.nextInt(193) + 8;
        int y = random.nextInt(193) + 8;
        
        if (chance < PROB_INVENCIBILIDAD) return new Invencibilidad(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR) return new Construir(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR + PROB_CONGELAR) return new Congelar(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR + PROB_CONGELAR + PROB_SUBIRNIVEL) return new SubirNivel(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR + PROB_CONGELAR + PROB_SUBIRNIVEL + PROB_DESTRUIR) return new Destruir(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR + PROB_CONGELAR + PROB_SUBIRNIVEL + PROB_DESTRUIR + PROB_VIDAEXTRA) return new VidaExtra(x, y);
        else if (chance < PROB_INVENCIBILIDAD + PROB_CONSTRUIR + PROB_CONGELAR + PROB_SUBIRNIVEL + PROB_DESTRUIR + PROB_VIDAEXTRA + PROB_MAXIMONIVEL) return new MaximoNivel(x, y);


        // Si es q falla (No falla)
        return new Invencibilidad(x, y);
    }

}
