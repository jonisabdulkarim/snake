import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CoreMechanics {

    private static Board board;
    private static final Coordinate START_COORDS = new Coordinate(10, 5);

    public Snake snake;

    public void initialiseGame() {
        board = Board.getInstance();
        initialiseSnakeStart();
        randomisePointGeneration();

    }

    public void initialiseSnakeStart() {
        snake = new Snake(START_COORDS);
        //board.placeSnake(snake.getSnake());
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
        // TODO: finish implementation after completing key listener
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
                // TODO: moveSnakeOnBoard()
                // View.getView().repaint();
            }
        };

        timer.schedule(task, 0, 250);
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

    public void drawBoard() {
        // may be removed
    }

}
