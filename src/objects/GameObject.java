package objects;

import utilities.Coordinate;
import java.awt.*;

public abstract class GameObject {

    private Color colour;
    private Coordinate position;

    // returns position of game object
    public Coordinate getPosition() {
        return position;
    }

    // sets position of this object by cardinals
    public void setPosition(int x, int y){
        position = new Coordinate(x, y);
    }

    // sets position by coordinate class object
    public void setPosition(Coordinate coord){
        position = coord;
    }

    // gets colour of this object
    public Color getColour() {
        return colour;
    }

    // sets colour of this object
    public void setColour(Color colour) {
        this.colour = colour;
    }
}
