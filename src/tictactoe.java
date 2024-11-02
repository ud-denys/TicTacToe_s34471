import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class tictactoe extends JFrame {
    private JButton[][] buttons = new JButton[5][5];
    private tictactoelib lib = new tictactoelib();
    private JLabel turnLabel = new JLabel("Turn: Player X", SwingConstants.CENTER);
    private char currentPlayer = 'X';

    public tictactoe() {
        lib.initializeBoard(); // Initialize board

        // Layout setup
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(5, 5));
        boardPanel.setBackground(Color.DARK_GRAY);
        initializeGUI(boardPanel); // Initialize buttons

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Label for current turn
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        turnLabel.setForeground(Color.WHITE);
        controlPanel.add(turnLabel, BorderLayout.CENTER);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBackground(new Color(70, 70, 70));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetGame());
        controlPanel.add(resetButton, BorderLayout.EAST);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Window settings
        setTitle("Tic-Tac-Toe");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setVisible(true);
    }

    private void initializeGUI(JPanel boardPanel) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 100));
                buttons[i][j].setBackground(new Color(50, 50, 50));
                buttons[i][j].setForeground(Color.LIGHT_GRAY);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBorder(new LineBorder(Color.BLACK, 2));

                final int x = i, y = j;
                buttons[i][j].addActionListener(e -> handleMove(x, y));
                buttons[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (buttons[x][y].getText().equals(" "))
                            buttons[x][y].setBackground(new Color(70, 70, 70)); // Hover effect
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        buttons[x][y].setBackground(new Color(50, 50, 50));
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }
    }

    private void handleMove(int x, int y) {
        if (lib.makeMove(x, y, currentPlayer)) {
            buttons[x][y].setText(String.valueOf(currentPlayer));
            buttons[x][y].setForeground(currentPlayer == 'X' ? Color.CYAN : Color.ORANGE);

            // Update the board state using getBoard for verification
            char[][] board = lib.getBoard();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    buttons[i][j].setText(board[i][j] == ' ' ? "" : String.valueOf(board[i][j]));
                }
            }

            char winner = lib.checkWin();
            if (winner == 'T') showEndGameMessage("It's a tie!");
            else if (winner != ' ') showEndGameMessage(winner + " wins!");
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                turnLabel.setText("Turn: Player " + currentPlayer);
            }
        }
    }

    private void showEndGameMessage(String message) {
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(label, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, panel, "Game Over", JOptionPane.PLAIN_MESSAGE);
        resetGame(); // Reset after message
    }

    private void resetGame() {
        lib.resetBoard(); // Reset board
        currentPlayer = 'X';
        turnLabel.setText("Turn: Player X");

        // Retrieve the reset board and update GUI
        char[][] board = lib.getBoard();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText(board[i][j] == ' ' ? "" : String.valueOf(board[i][j]));
                buttons[i][j].setBackground(new Color(50, 50, 50));
                buttons[i][j].setForeground(Color.LIGHT_GRAY);
            }
        }
    }

    public static void main(String[] args) {
        new tictactoe();
    }
}
