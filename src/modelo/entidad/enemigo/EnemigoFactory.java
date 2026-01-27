package modelo.entidad.enemigo;


public class EnemigoFactory {

    public static TanqueEnemigo crearEnemigo(TipoEnemigo tipo, int x, int y, boolean dropPowerUp) {
        
        switch (tipo) {
            case BASICO:        return new TanqueBasico(x, y, 1.0, dropPowerUp); 

            case ALTA_CADENCIA: return new TanqueAltaCadencia(x, y, 1.0, dropPowerUp);
                
            case RAPIDO:        return new TanqueRapido(x, y, 1.0, dropPowerUp);
                
            case BLINDADO:      return new TanqueBlindado(x, y, 1.0, dropPowerUp);

            default:            return null;
        }
    }

}
