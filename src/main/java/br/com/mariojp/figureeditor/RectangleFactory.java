package br.com.mariojp.figureeditor;

import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(Point p, int size) {
        return new Rectangle2D.Double(p.x, p.y, size, size);
    }
}