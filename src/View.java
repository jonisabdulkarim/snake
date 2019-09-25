import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel implements ActionListener {
    private static int score;

    private volatile static boolean pause;
    private volatile static boolean newGame;

    private static JLabel gameOverMessage;

    private static JButton pauseBut;
    private static JButton resumeBut;
    private static JButton newBut;

    private static View view;
    private static Board board;

    private View() {
        init();
        score = 0;
        pause = false;
        newGame = false;
    }

    static View getView() {
        if (view == null)
            view = new View();

        return view;
    }

    void addScore(){
        score++;
    }

    private void init() {
        //Create frame
        JFrame frame = new JFrame("Snake");
        frame.setSize(700, 625);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for buttons
        JPanel buttonBar = new JPanel();
        buttonBar.requestFocus(false);
        frame.add(buttonBar, BorderLayout.EAST);
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.Y_AXIS));

        JLabel s = new JLabel("BUTTONS:");
        buttonBar.add(s);

        //Buttons
        pauseBut = new JButton("PAUSE");
        buttonBar.add(pauseBut);
        pauseBut.setBackground(new Color(59, 89, 182));
        pauseBut.setBounds(50, 50, 250, 100);
        pauseBut.setFocusPainted(false);
        pauseBut.setForeground(Color.WHITE);
        pauseBut.setActionCommand("pause");
        pauseBut.addActionListener(this);

        resumeBut = new JButton("RESUME");
        buttonBar.add(resumeBut);
        resumeBut.setBackground(new Color(59, 89, 182));
        resumeBut.setFocusPainted(false);
        resumeBut.setForeground(Color.WHITE);
        resumeBut.setVisible(false);//make resume invisible
        resumeBut.setActionCommand("resume");
        resumeBut.addActionListener(this);

        newBut = new JButton("NEW GAME");
        buttonBar.add(newBut);
        newBut.setBackground(new Color(59, 89, 182));
        newBut.setFocusPainted(false);
        newBut.setForeground(Color.WHITE);
        newBut.setActionCommand("newGame");
        newBut.addActionListener(this);

        gameOverMessage = new JLabel("Game Over!");
        buttonBar.add(gameOverMessage);
        gameOverMessage.setVisible(false);

        frame.add(this);
        board = Board.getInstance();

        setFocusable(true);
        requestFocus();
    }

    void gameOver(){
        gameOverMessage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("pause".equals(e.getActionCommand())) {
            pause = true;
            pauseBut.setVisible(false);
            resumeBut.setVisible(true);
        }
        else if("resume".equals(e.getActionCommand())) {
            pause = false;
            pauseBut.setVisible(true);
            resumeBut.setVisible(false);
        }
        else if("newGame".equals(e.getActionCommand())) {
            newGame = true;
        }
    }

    void resetView(){
        gameOverMessage.setVisible(false);
        pauseBut.setVisible(true);
        resumeBut.setVisible(false);
        newBut.setVisible(true);
        newGame = false;
        pause = false;
        score = 0;
        requestFocus();
    }

    char getStatus() {
        if (newGame) {
            return 'N';
        }
        else if (pause) {
            return 'P';
        }
        else if (!pause) {
            return 'R';
        }
        else{
            throw new IllegalStateException();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the grid
        g.fillRect(0, 0, 28 * 21, 28 * 21);
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                g.setColor(board.getTile(i, j).getColour());
                g.fillRect(28 * i, 28 * j, 25, 25);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 500, 40);// Display the score
    }
}
