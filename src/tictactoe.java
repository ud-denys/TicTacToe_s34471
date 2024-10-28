import javax.swing.*;
import java.awt.*;

public class tictactoe {

    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private final int SIZE = 5;



    public tictactoe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));
        buttons = new JButton[SIZE][SIZE];

        initializeBoard();

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 24));
        resetButton.addActionListener(e -> resetGame());
        frame.add(resetButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void initializeBoard() {
        String[][] initialBoard = tictactoelib.getBoardStateJNI();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton(initialBoard[i][j]);
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setEnabled(initialBoard[i][j].isEmpty());

                final int row = i;
                final int col = j;

                buttons[row][col].addActionListener(e -> handleButtonClick(row, col));

                boardPanel.add(buttons[i][j]);
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        tictactoelib.makeMoveJNI(row, col);
        updateBoard();
    }

    private void updateBoard() {

        String[][] boardState = tictactoelib.getBoardStateJNI();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText(boardState[i][j]);
                buttons[i][j].setEnabled(boardState[i][j].isEmpty());
            }
        }
        updateStatusLabel();
    }

    private void updateStatusLabel() {

        String currentPlayer = tictactoelib.getCurrentPlayerJNI();
        statusLabel.setText(currentPlayer + "'s turn");
    }

    private void resetGame() {

        tictactoelib.resetGameJNI();
        updateBoard();
    }

    public static void main(String[] args) {
        System.load("C:\\Users\\s34471\\IdeaProjects\\tictactoelib_s34471\\cmake-build-debug\\libtictactoelib.dll");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new tictactoe();
            }
        });
    }
}