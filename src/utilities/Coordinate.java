package utilities;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public Coordinate(Coordinate coord) {
        setPosition(coord);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addAndSet(Coordinate other){
        setX(x + other.getX());
        setY(y + other.getY());
    }

    public Coordinate add(Coordinate other){
        return new Coordinate(other.getX() + x, other.getY() + y);
    }

    public void setPosition(Coordinate coords){
        setX(coords.getX());
        setY(coords.getY());
    }

    public String toString(){
        return "utilities.Coordinate is x = " + x + " and y = " + y;
    }
}