import objects.Body;
import objects.GameObject;
import objects.Head;
import objects.Point;

import java.awt.*;

public class Tile {

    private Color colour;
    private GameObject object;

    public Tile() {
        setColour(Color.BLACK);
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public char hasObject() {
        if (object == null) return 'X';
        else if (object.getClass() == Head.class) {
            return 'H';
        } else if (object.getClass() == Body.class) {
            return 'B';
        } else if (object.getClass() == Point.class) {
            return 'P';
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        if(object == null)
            throw new NullPointerException();

        this.object = object;
        setColour(object.getColour());
    }

    public void removeObject() {
        this.object = null;
        setColour(Color.BLACK);
    }
}