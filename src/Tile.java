import objects.*;
import objects.Point;

import java.awt.*;

class Tile {

    private Color colour;
    private GameObject object;

    Tile() {
        setColour(Color.BLACK);
    }

    Color getColour() {
        return colour;
    }

    void setColour(Color colour) {
        this.colour = colour;
    }

    char hasObject() {
        if (object == null) return 'X';
        else if (object.getClass() == Head.class) {
            return 'H';
        } else if (object.getClass() == Body.class) {
            return 'B';
        } else if (object.getClass() == Point.class) {
            return 'P';
        } else if(object.getClass() == Wall.class) {
            return 'W';
        } else {
            throw new IllegalArgumentException();
        }
    }

    GameObject getObject() {
        return object;
    }

    void setObject(GameObject object) {
        if(object == null)
            throw new NullPointerException();

        this.object = object;
        setColour(object.getColour());
    }

    void removeObject() {
        this.object = null;
        setColour(Color.BLACK);
    }
}