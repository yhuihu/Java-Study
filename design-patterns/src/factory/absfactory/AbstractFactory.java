package factory.absfactory;

import factory.absfactory.color.Color;
import factory.absfactory.shape.Shape;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see factory.absfactory
 **/
public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
