package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Clique para inserir figuras");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

         // Botões para trocar a fábrica
            JButton circleBtn = new JButton("Círculo");
            circleBtn.addActionListener(e -> panel.setShapeFactory(new CircleFactory()));

            JButton rectBtn = new JButton("Quadrado");
            rectBtn.addActionListener(e -> panel.setShapeFactory(new RectangleFactory()));
            
            JButton colorBtn = new JButton("Escolha a cor");
            colorBtn.addActionListener(e -> {
            	Color selected = JColorChooser.showDialog(frame, "Escolha uma cor", panel.getCurrentColor());
                if (selected != null) panel.setCurrentColor(selected);
           });
            
            JButton clearBtn = new JButton("Limpar");
            clearBtn.addActionListener(e -> panel.clear());
            
            JButton exportPNGBtn = new JButton("Exportar PNG");
            exportPNGBtn.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File("figuras.png"));
                if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    panel.exportToPNG(chooser.getSelectedFile());
                }
            });
            JPanel topBar = new JPanel();
            topBar.add(circleBtn);
            topBar.add(rectBtn);
            topBar.add(colorBtn);
            topBar.add(clearBtn);
            topBar.add(exportPNGBtn);
            
            frame.setLayout(new BorderLayout());
            frame.add(topBar, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
