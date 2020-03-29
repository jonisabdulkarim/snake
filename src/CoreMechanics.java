import objects.Head;
import objects.Point;

import utilities.Coordinate;
import utilities.Keys;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class CoreMechanics {

    private static final Coordinate START_COORDS = new Coordinate(10, 5);
    private static final int DELAY = 500;

    private static Board board;
    private static Snake snake;
    private static View view;
    private static Keys keys;
    private static Timer timer;

    void createGame() {
        view = View.getView();
        initialiseGame();

        keys = new Keys(snake.getDirection());
        view.addKeyListener(keys);

        startTimer();
    }

    private void initialiseGame() {
        board = Board.getInstance();
        initialiseSnakeStart();
        randomisePointGeneration();
    }

    private void initialiseSnakeStart() {
        snake = new Snake(START_COORDS);
        board.placeSnake(snake);
    }

    private void randomisePointGeneration() {
        Point point = new Point();

        Coordinate pointPosition = randomPosition();
        while (board.getTile(pointPosition).hasObject() != 'X') {
            pointPosition = randomPosition();
        }

        point.setPosition(pointPosition);
        board.placeObjectOnTile(point);
    }

    private Coordinate randomPosition() {
        Random r = new Random();
        int x = 1 + r.nextInt(19);
        int y = 1 + r.nextInt(19);
        return new Coordinate(x, y);
    }

    private void moveSnakeOnBoard() {
        Coordinate vector = directionToCoordinate(snake.getHead());
        if (collisionDetection(vector, snake.getHead())) {
            gameOver();
        } else {
            board.removeSnake(snake);
            board.updateSnake(snake, vector);
            board.placeSnake(snake);
        }
    }

    private Coordinate directionToCoordinate(Head head) {
        char direction = head.getDirection();
        switch (direction) {
            case 'U':
                return new Coordinate(0, -1);
            case 'D':
                return new Coordinate(0, 1);
            case 'L':
                return new Coordinate(-1, 0);
            case 'R':
                return new Coordinate(1, 0);
            default:
                throw new IllegalArgumentException();
        }
    }

    private void correctDirection() {
        char snakeDirection = snake.getDirection();
        char keysDirection = keys.getDirection();

        if (!((snakeDirection == 'U' && keysDirection == 'D') ||
                (snakeDirection == 'R' && keysDirection == 'L') ||
                (snakeDirection == 'L' && keysDirection == 'R') ||
                (snakeDirection == 'D' && keysDirection == 'U'))) {
            snake.setDirection(keysDirection);
        }
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                checkButtonStatus();
                correctDirection();
                moveSnakeOnBoard();
                view.repaint();
            }
        };

        timer.scheduleAtFixedRate(task, DELAY, DELAY); // TODO: sort out lag issue
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private boolean collisionDetection(Coordinate vector, Head head) {
        Coordinate newPosition = head.getPosition().add(vector);
        Tile tileOfHead = board.getTile(newPosition);

        switch (tileOfHead.hasObject()) {
            case 'P':
                eatPoint();
            case 'X':
                return false;
            case 'B':
            case 'W':
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    private void eatPoint() {
        snake.addBody();
        randomisePointGeneration();
        view.addScore();
    }

    private void gameOver() {
        stopTimer();
        view.gameOver();

        while (view.getStatus() != 'N') {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }

        newGame();
    }

    private void pauseGame() {
        stopTimer();
        while (view.getStatus() != 'R' && view.getStatus() != 'N') {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
        startTimer();
        if (view.getStatus() == 'N')
            newGame();
    }

    private void newGame() {
        stopTimer();
        board.clearBoard();
        initialiseSnakeStart();
        randomisePointGeneration();

        keys = new Keys(snake.getDirection());
        view.addKeyListener(keys);

        startTimer();
    }

    private void checkButtonStatus() {
        char status = view.getStatus();
        switch (status) {
            case 'P':
                pauseGame();
                break;
            case 'R':
                break;
            case 'N':
                view.resetView();
                newGame();
                break;
            default:
                throw new IllegalStateException();
        }
    }
}
