import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private boolean pause;
    private static long score;
    private static int level;
    private static Board board;

    private static View view;

    private View() {
        init();
    }

    public static View getView() {
        if (view == null)
            view = new View();

        return view;
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
        JButton pauseBut = new JButton("PAUSE");
        buttonBar.add(pauseBut);
        pauseBut.setBackground(new Color(59, 89, 182));
        pauseBut.setBounds(50, 50, 250, 100);
        pauseBut.setFocusPainted(false);
        pauseBut.setForeground(Color.WHITE);

        JButton resumeBut = new JButton("RESUME");
        buttonBar.add(resumeBut);
        resumeBut.setBackground(new Color(59, 89, 182));
        resumeBut.setFocusPainted(false);
        resumeBut.setForeground(Color.WHITE);
        resumeBut.setVisible(false);//make resume invisible

        JButton resetBut = new JButton("NEW GAME");
        buttonBar.add(resetBut);
        resetBut.setBackground(new Color(59, 89, 182));
        resetBut.setFocusPainted(false);
        resetBut.setForeground(Color.WHITE);

        frame.add(this);
        board = Board.getInstance();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the grid
        g.fillRect(0, 0, 28 * 21, 28 * 21);
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                g.setColor(Board.getInstance().getTile(i, j).getColour());
                g.fillRect(28 * i, 28 * j, 25, 25);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 500, 40);// Display the score
        g.drawString("Level: " + level, 500, 60);// Display the level
    }
}
