import objects.Head;
import objects.Point;
import objects.SnakePart;
import utilities.Coordinate;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CoreMechanics {

    private static final Coordinate START_COORDS = new Coordinate(10, 5);
    private static final int DELAY = 500;

    private static Board board;
    private static Snake snake;
    private static View view;

    public void createGame(){
        view = View.getView();
        initialiseGame();
        startTimer();
    }

    public void initialiseGame() {
        board = Board.getInstance();
        initialiseSnakeStart();
        randomisePointGeneration();
    }

    public void initialiseSnakeStart() {
        snake = new Snake(START_COORDS);
        board.placeSnake(snake);
    }

    public void randomisePointGeneration() {
        Point point = new Point();
        point.setPosition(randomPosition());
        board.placeObjectOnTile(point);
    }

    public Coordinate randomPosition(){
        Random r = new Random();
        int x = 1 + r.nextInt(19);
        int y = 1 + r.nextInt(19);
        return new Coordinate(x, y);
    }

    public void moveSnakeOnBoard(){
        Coordinate vector = directionToCoordinate(snake.getHead());
        board.removeSnake(snake);
        for (SnakePart part : snake) {
            part.getPosition().add(vector);
        }
        board.placeSnake(snake);
    }

    public Coordinate directionToCoordinate(Head head){
        char direction = head.getDirection();
        switch (direction){
            case 'U':
                return new Coordinate(0,1);
            case 'D':
                return new Coordinate(0,-1);
            case 'L':
                return new Coordinate(-1, 0);
            case 'R':
                return new Coordinate(1, 0);
            default:
                throw new IllegalArgumentException();
        }
    }

    public void startTimer(){
        Timer timer =  new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                moveSnakeOnBoard();
                view.repaint();
            }
        };

        timer.schedule(task, 0, DELAY);
    }

    public void sendBoard() {
        // may be removed
    }

    public void gameOver() {
        // backburner
    }
    public void pauseGame() {
        // backburner
    }

}
