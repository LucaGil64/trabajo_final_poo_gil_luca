package vista;

import java.awt.*;
import javax.swing.*;
import modelo.Mapa;

public class ContenedorJuego extends JPanel {

    public ContenedorJuego() {
        this.setBorder(BorderFactory.createEmptyBorder(59, 59, 59, 59));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Insets insets = getInsets(); // Lee el EmptyBorder
        g.translate(insets.left, insets.top); // Mueve el origen del dibujo

        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(3.0, 3.0); // Esto duplicará el tamaño de todo (8px se verán como 16px)
        

        Mapa.getInstance().dibujarTodo(g);

        
    
        // Opcional: Resetear el translate para no afectar otros dibujos
        g.translate(-insets.left, -insets.top);
    }

}
