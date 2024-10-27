import javax.swing.*;
import java.awt.*;

public class tictactoe {

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private final int SIZE = 5;
    private boolean isPlayerX = true;

    public tictactoe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
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

                buttons[row][col].addActionListener(e -> handleButtonClick(row, col));

                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private void handleButtonClick(int row, int col) {
        buttons[row][col].setText(getCurrentPlayer());
        buttons[row][col].setEnabled(false);
        isPlayerX = !isPlayerX;
        updateStatusLabel();
    }

    private String getCurrentPlayer() {
        return isPlayerX ? "X" : "O";
    }

    private void updateStatusLabel() {
        if (isPlayerX) {
            statusLabel.setText("Player X's turn");
        } else {
            statusLabel.setText("Player O's turn");
        }
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
