import javax.swing.*;
import java.awt.*;

public class tictactoe {

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private final int SIZE = 5;

    public tictactoe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));
        buttons = new JButton[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);

                final int row = i;
                final int col = j;

                // TODO: Add action register for button click

                boardPanel.add(buttons[i][j]);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new tictactoe();
            }
        });
    }
}
