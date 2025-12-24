package modelo;

import java.awt.Rectangle;
import modelo.proyectil.Proyectil;

public interface Impactable {

    public void recibirImpacto(Proyectil proyectil);
    public Rectangle getBounds();
    public boolean esSolido();

}
