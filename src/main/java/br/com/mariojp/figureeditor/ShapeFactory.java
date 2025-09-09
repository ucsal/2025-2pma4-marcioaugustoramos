package br.com.mariojp.figureeditor;


import java.awt.Shape;
import java.awt.Point;

public interface ShapeFactory {
    Shape createShape(Point p, int size);
}