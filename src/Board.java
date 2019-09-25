import objects.GameObject;
import objects.SnakePart;
import objects.Wall;
import utilities.Coordinate;

public class Board {

    private static Board board;

    private Tile[][] tiles;

    // Singleton implementation
    private Board() {
        initialiseBoard();
    }

    static Board getInstance(){
        if(board == null)
            board = new Board();

        return board;
    }

    private void initialiseBoard() {
        tiles = new Tile[21][21];
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                tiles[i][j] = new Tile();
                buildWall(new Coordinate(i, j));
            }
        }
    }

    private void buildWall(Coordinate position){
        int x = position.getX();
        int y = position.getY();
        if (x == 0 || y ==0 || x == 20 || y == 20) {//make a gray border around the grid
            getTile(x, y).setObject(new Wall());
        }
    }

    Tile[][] getTiles(){
        return tiles;
    }

    Tile getTile(int x, int y){
        return tiles[x][y];
    }

    Tile getTile(Coordinate position){
        return tiles[position.getX()][position.getY()];
    }

    void clearBoard() {
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                getTile(i, j).removeObject();
            }
        }
    }

    public void updateBoard() {
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                getTile(i, j).setColour(getTile(i, j).getObject().getColour());
            }
        }
    }

    void updateSnake(Snake snake, Coordinate vector) {
        for (SnakePart part : snake){
            if(part.getNext() == null){ // head of the snake
                part.getPosition().addAndSet(vector);
            }
            else {
                part.setPosition(part.getNext().getPosition());
            }
        }
    }

    void placeObjectOnTile(GameObject object){
        Tile tile = getTile(object.getPosition());
        tile.setObject(object);
    }

    void placeSnake(Snake snake){
        for (SnakePart part : snake){
            placeObjectOnTile(part);
        }
    }

    void removeSnake(Snake snake){
        for (SnakePart part : snake){
            removeObject(part.getPosition());
        }
    }

    @SuppressWarnings("WeakerAccess")
    void removeObject(Coordinate position){
        getTile(position).removeObject();
    }
}
