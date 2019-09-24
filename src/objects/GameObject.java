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

    // sets position by coordinate class object
    public void setPosition(Coordinate coord){
        if(position == null)
            position = new Coordinate(coord);

        position.setPosition(coord);
    }

    public void setPosition(int x, int y){
        if(position == null)
            position = new Coordinate(x, y);

        position.setX(x);
        position.setY(y);
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
