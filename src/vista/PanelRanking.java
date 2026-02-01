package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PanelRanking extends JPanel{

    JLabel labelRanking;
    JTable tablaRanking;
    DefaultTableModel modelo;
    JScrollPane scrollPane;

    public PanelRanking() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        labelRanking = new JLabel("Ranking");
        labelRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelRanking.setFont(FuenteUtils.getFuente().deriveFont(30f));
        labelRanking.setForeground(Color.YELLOW);

        // 1. Definir columnas
        String[] columnas = {"Posicion", "Nombre", "Puntos"};
        
        // 2. Crear modelo de tabla (hacemos que las celdas no sean editables)
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 3. Llenar la tabla del 1 al 10 con datos vacíos o de ejemplo
        for (int i = 1; i <= 10; i++) {
            modelo.addRow(new Object[]{i + "º", "---", "0"});
        }

        // 4. Configurar la JTable
        tablaRanking = new JTable(modelo);
        tablaRanking.setBackground(Color.BLACK);
        tablaRanking.setForeground(Color.WHITE);
        tablaRanking.setGridColor(Color.YELLOW);
        tablaRanking.setRowHeight(35);
        tablaRanking.setFont(FuenteUtils.getFuente().deriveFont(10f));
        tablaRanking.setSelectionBackground(Color.DARK_GRAY);

        // 5. Personalizar el encabezado (Header)
        tablaRanking.getTableHeader().setBackground(Color.BLACK);
        tablaRanking.getTableHeader().setForeground(Color.YELLOW);
        tablaRanking.getTableHeader().setFont(FuenteUtils.getFuente().deriveFont(20f));
        tablaRanking.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // 5.5 Comfigurar tamaño de las columnas
        TableColumnModel columnModel = tablaRanking.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(0).setMaxWidth(200);

        columnModel.getColumn(2).setPreferredWidth(160);
        columnModel.getColumn(2).setMaxWidth(160);

        columnModel.getColumn(1).setPreferredWidth(400);

        // 6. Centrar el contenido de las celdas
        DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
        centroRenderer.setHorizontalAlignment(JLabel.CENTER);
        centroRenderer.setBackground(Color.BLACK);
        centroRenderer.setForeground(Color.WHITE);
        
        for (int i = 0; i < tablaRanking.getColumnCount(); i++) {
            tablaRanking.getColumnModel().getColumn(i).setCellRenderer(centroRenderer);
        }

        // 7. Agregar a un ScrollPane (necesario para ver el encabezado)
        scrollPane = new JScrollPane(tablaRanking);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        


        this.add(labelRanking);
        this.add(Box.createVerticalStrut(50));
        this.add(scrollPane);
    }

    public DefaultTableModel getModelo() {
        return this.modelo;
    }

}
