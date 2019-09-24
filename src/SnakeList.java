import objects.Body;
import objects.Head;
import objects.SnakePart;

public interface SnakeList {
    void addBody();
    SnakePart getPart(int index);
    Head getHead();
    SnakePart getSnake();
    int size();
}
