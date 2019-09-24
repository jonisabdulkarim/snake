package objects;

public abstract class SnakePart extends GameObject {
    private SnakePart next;

    // Create starting position for this body of snake
    public SnakePart(SnakePart next) {
        setNext(next);
    }

    // sets this body to be linked to next body/head
    public void setNext(SnakePart next){
        this.next = next;
    }

    public SnakePart getNext() {
        return next;
    }
}