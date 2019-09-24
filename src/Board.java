import objects.GameObject;
import objects.SnakePart;
import utilities.Coordinate;

import java.awt.*;

public class Board {

    private static Board board;

    private Tile[][] tiles;

    // Singleton implementation
    private Board() {
        initialiseBoard();
    }

    public static Board getInstance(){
        if(board == null)
            board = new Board();

        return board;
    }

    public void initialiseBoard() {
        tiles = new Tile[21][21];
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                tiles[i][j] = new Tile();
                initialTilePaint(new Coordinate(i, j));
            }
        }
    }

    public void initialTilePaint(Coordinate position){
        int x = position.getX();
        int y = position.getY();
        if (x == 0 || y ==0 || x == 20 || y == 20) {//make a gray border around the grid
            getTile(x, y).setColour(Color.GRAY);
        } else {
            getTile(x, y).setColour(Color.BLACK);
        }
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public Tile getTile(Coordinate position){
        return tiles[position.getX()][position.getY()];
    }

    public void clearBoard() {
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

    public void placeObjectOnTile(GameObject object){
        Tile tile = getTile(object.getPosition());
        tile.setObject(object);
    }

    public void placeSnake(Snake snake){
        for (SnakePart part : snake){
            placeObjectOnTile(part);
        }
    }

    public void removeSnake(Snake snake){
        for (SnakePart part : snake){
            removeObject(part.getPosition());
        }
    }

    public void removeObject(Coordinate position){
        getTile(position).removeObject();
    }
}
