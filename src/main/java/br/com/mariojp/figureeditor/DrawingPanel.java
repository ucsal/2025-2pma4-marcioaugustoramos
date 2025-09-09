
package br.com.mariojp.figureeditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<ColoredShape> shapes = new ArrayList<>();
    //private Point startDrag = null;
    private ShapeFactory shapeFactory = new CircleFactory();
    private Color currentColor = new Color(30, 144, 255);
    
    	DrawingPanel() {
        
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int size = Math.max( DEFAULT_SIZE, 10);
                    Shape s =  shapeFactory.createShape(e.getPoint(), size);
                
                    shapes.add(new ColoredShape(s, currentColor));
                    repaint();
                }
            }
        };
        addMouseListener(mouse);        
        addMouseMotionListener(mouse);

    }
    void setShapeFactory(ShapeFactory factory) {
        this.shapeFactory = factory;
    }
    void clear() {
        shapes.clear();
        repaint();
    }
    
    void setCurrentColor(Color color) {
        this.currentColor = color;
    }
    
    Color getCurrentColor() {
        return this.currentColor;
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ColoredShape cs : shapes) {
            g2.setColor(new Color(30,144,255));
            g2.fill(cs.shape);
            g2.setColor(new Color(0,0,0,70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(cs.shape);
        }

        g2.dispose();
    }
    
    void exportToPNG(File file) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        g2.dispose();
        try {
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "Exportado para " + file.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar PNG: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    	private static class ColoredShape {
        Shape shape;
        Color color;

        ColoredShape(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }
}
}
