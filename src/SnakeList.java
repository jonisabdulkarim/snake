import objects.Body;
import objects.Head;
import objects.SnakePart;

public interface SnakeList {
    void addBody();
    Body getBody(int index);
    Head getHead();
    SnakePart getSnake();
    int size();
}
