import objects.Body;
import objects.Head;
import objects.SnakePart;
import utilities.Coordinate;

import java.util.Iterator;

// LinkedList implementation of objects.SnakePart
public class Snake implements SnakeList, Iterable<SnakePart> {

    private SnakePart head;
    private SnakePart body;

    public Snake(Coordinate start) {
        head = body = new Head(start);
        addBody();
        addBody();
    }

    @Override
    public void addBody() {
        body = new Body(body);
    }

    // returns length of snake (including head)
    @Override
    public int size() {
        int count = 0;

        for(SnakePart part : this){
            count++;
        }

        return count;
    }

    // returns the head of the snake
    @Override
    public Head getHead() {
        return (Head) head;
    }

    // returns back of snake, which is linked to head
    @Override
    public SnakePart getSnake() {
        return body;
    }

    // returns body at specified index
    // starting from back
    @Override
    public Body getBody(int index) {
        // TODO: implement
        return new Body(null);
    }

    // allows Snake to be iterated
    // with a for-each loop
    @Override
    public Iterator<SnakePart> iterator() {
        return new Iterator<SnakePart>() {
            SnakePart temp = new Body(body);

            @Override
            public boolean hasNext() {
                return temp.getNext() != null;
            }

            @Override
            public SnakePart next() {
                temp = temp.getNext();
                return temp;
            }
        };
    }
}