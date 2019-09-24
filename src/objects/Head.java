package objects;

import utilities.Coordinate;

import java.awt.*;

public class Head extends SnakePart {

    private char direction;

    //Create starting position for head of snake
    public Head(Coordinate startPosition) {
        super(null);
        setPosition(startPosition);
        setColour(Color.RED);
        setDirection('L');
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
