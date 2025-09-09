package br.com.mariojp.figureeditor;

import java.awt.Shape;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape(Point p, int size) {
        return new Ellipse2D.Double(p.x, p.y, size, size);
    }
}