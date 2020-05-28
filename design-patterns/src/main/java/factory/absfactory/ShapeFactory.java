package factory.absfactory;

import factory.absfactory.color.Color;
import factory.absfactory.shape.Circle;
import factory.absfactory.shape.Rectangle;
import factory.absfactory.shape.Shape;
import factory.absfactory.shape.Square;

/**
 * @author Tiger
 * @date 2020-03-09
 * @see factory.absfactory
 **/
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
