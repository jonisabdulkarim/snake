package objects;

import java.awt.*;

public class Body extends SnakePart {
    // Create starting position for this body of snake
    public Body(SnakePart next) {
        super(next);
        setColour(Color.BLUE);
        setPosition(next.getPosition().getX() + 1, next.getPosition().getY());
    }
}