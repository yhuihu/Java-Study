package factory.absfactory;

import factory.absfactory.color.Blue;
import factory.absfactory.color.Color;
import factory.absfactory.color.Green;
import factory.absfactory.color.Red;
import factory.absfactory.shape.Shape;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see factory.absfactory
 **/
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }
}
