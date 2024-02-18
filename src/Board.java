import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.Random;

public class Board extends JPanel {
    private final int rows;
    private final int cols;
    private final Cell[][] board;
    private final int cellSize = 20;
    private Piece piece;
    private int playerX = 0;
    private int playerY = 0;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new SimpleCell[rows][cols];
        this.piece = new Piece(0, 0); // Start position of the piece

        // Initialize cells with a mix of common, obstacle, and market cells
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double chance = rand.nextDouble();
                if (chance < 0.1) {
                    board[i][j] = new SimpleCell(i, j, CellType.OBSTACLE);
                } else if (chance < 0.2) {
                    board[i][j] = new SimpleCell(i, j, CellType.MARKET);
                } else {
                    board[i][j] = new SimpleCell(i, j, CellType.COMMON);
                }
            }
        }

        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePiece(e);
            }
        });
        setFocusable(true);
    }

    private void movePiece(KeyEvent e) {
        int newRow = piece.getRow();
        int newCol = piece.getCol();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                newRow = Math.max(newRow - 1, 0);
                break;
            case KeyEvent.VK_DOWN:
                newRow = Math.min(newRow + 1, rows - 1);
                break;
            case KeyEvent.VK_LEFT:
                newCol = Math.max(newCol - 1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                newCol = Math.min(newCol + 1, cols - 1);
                break;
        }
        // Check if the cell is not an obstacle before moving
        if (board[newRow][newCol].getType() != CellType.OBSTACLE) {
            piece.move(newRow, newCol);
            // Trigger quiz or other actions based on the cell type
            handleCellAction(newRow, newCol);
        }
        repaint();
    }

    private void handleCellAction(int row, int col) {
        CellType cellType = board[row][col].getType();
        if (cellType == CellType.COMMON && new Random().nextDouble() < 0.3) {
            new PopUpQuiz(null).display(); // Display quiz with a 30% chance on common cells
        } else if (cellType == CellType.MARKET) {
            new PopUpMarket(null).display(); // Display market on market cells
        }
        // else if (cellType == CellType.END) {
        // new PopUpEnd(null).display(); // Display end game message on end cell
        // }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Use the smaller of the two dimensions to ensure the board remains square
        int size = Math.min(getWidth(), getHeight());
        int cellSize = size / Math.max(rows, cols);

        // Calculate offsets to center the board in the panel
        int xOffset = (getWidth() - (cellSize * cols)) / 2;
        int yOffset = (getHeight() - (cellSize * rows)) / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = xOffset + j * cellSize;
                int y = yOffset + i * cellSize;
                Cell cell = board[i][j];

                // Determine color based on cell type
                Color fillColor;
                switch (cell.getType()) {
                    case OBSTACLE:
                        fillColor = Color.RED;
                        break;
                    case MARKET:
                        fillColor = Color.GREEN;
                        break;
                    default:
                        fillColor = Color.LIGHT_GRAY;
                        break;
                }

                g.setColor(fillColor);
                g.fillRect(x, y, cellSize, cellSize);

                // Additional details for MARKET cells
                if (cell.getType() == CellType.MARKET) {
                    g.setColor(Color.BLACK);
                    g.drawString("M", x + cellSize / 4, y + (3 * cellSize / 4));
                }

                // Outline for all cells
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);

                // Draw the piece if this cell has it
                if (i == piece.getRow() && j == piece.getCol()) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
                }
            }
        }
    }

    public void movePlayer(int dx, int dy) {
        // Update player's position
        playerX += dx;
        playerY += dy;

        // Check for bounds, obstacles, etc., before this in a real application

        // Increment the score for moving
        Main.setScore(Main.getScore() + 1); // Increment score by 1 for every move

        // Possibly update the GUI or internal state to reflect new player position
    }

}
