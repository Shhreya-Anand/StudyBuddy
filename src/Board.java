import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

// Assuming necessary imports are here

public class Board extends JPanel {
    private User user;
    private final int rows;
    private final int cols;
    private final Cell[][] board;
    private final int cellSize = 40;
    private Piece piece;
    private MainFrame mainFrame;

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


    public Board(int rows, int cols, User user) {
        super(new BorderLayout()); // Use BorderLayout for the main panel
        this.rows = rows;
        this.cols = cols;
        this.user = user;
        this.board = new SimpleCell[rows][cols];
        this.piece = new Piece(0, 0); // Start position of the piece

        initBoard(); // Initialize the game board
        setupUI(); // Setup UI components including above and below bars
    }

    private void initBoard() {
        // Initialize cells with a mix of common, obstacle, and market cells
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[0][0]= new SimpleCell(0, 0, CellType.COMMON);
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
    }

    private void setupUI() {
        JPanel aboveBar = new JPanel(new BorderLayout());
//        JLabel profileLabel = new JLabel("Profile: " + user.getName());
//        JLabel expLabel = new JLabel("EXP: " + user.getExp());
//
//        aboveBar.add(profileLabel, BorderLayout.WEST);
//        aboveBar.add(expLabel, BorderLayout.EAST);
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int x = j * cellSize;
                        int y = i * cellSize;
                        Cell cell = board[i][j];
                        if (cell.getType() == CellType.OBSTACLE) {
                            g.setColor(Color.RED);
                            g.fillRect(x, y, cellSize, cellSize);
                            g.setColor(Color.BLACK); // Color for the "M" symbol
                            g.drawString("XXX", x + cellSize / 4, y + (2 * cellSize / 3)); // Adjust to center "x"
//                    g.drawString("XXX", x + cellSize / 6, y + (2 * cellSize / 6));
                        } else if (cell.getType() == CellType.MARKET) {
                            g.setColor(Color.YELLOW); // Choose an appropriate color for market cells
                            g.fillRect(x, y, cellSize, cellSize);
                            g.setColor(Color.BLACK); // Color for the "M" symbol
                            g.drawString("M", x + cellSize / 4, y + (2 * cellSize / 3)); // Adjust to center "M"
                        } else {
                            g.setColor(Color.PINK);
                            g.fillRect(x, y, cellSize, cellSize);
                        }
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);

                        // Draw the piece
                        if (i == piece.getRow() && j == piece.getCol()) {
                            g.setColor(Color.BLUE);
                            g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 2, cellSize / 2);
                        }
                    }
                }

            }
        };
        gamePanel.setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePiece(e);
            }
        });

        add(aboveBar, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        // Optional: Configure and add a below bar if needed
        JPanel belowBar = new JPanel();
        // Add components to belowBar as needed
        add(belowBar, BorderLayout.SOUTH);
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

// Then, in your move completion logic (e.g., inside movePiece method), call:
        if (mainFrame != null) {
            mainFrame.updateBottomBar();
        }

        // Check if the cell is not an obstacle before moving
        if (board[newRow][newCol].getType() != CellType.OBSTACLE) {
            piece.move(newRow, newCol);
            // Trigger quiz or other actions based on the cell type
            handleCellAction(newRow, newCol);
        }
        updateEndCell();
        repaint();
    }

    private void updateEndCell() {
        if (user.getExp() >= 100) {
            // Set the rightmost bottom corner cell to END type
            board[rows - 1][cols - 1] = new SimpleCell(rows - 1, cols - 1, CellType.END);
            repaint(); // Repaint the board to reflect the change
        }
    }

    // Ensure this method is called after any action that might increase the user's EXP
    public void checkUserExp() {
        updateEndCell();
    }


    private void handleCellAction(int row, int col) {
        CellType cellType = board[row][col].getType();
        if (cellType == CellType.COMMON && new Random().nextDouble() < 0.3) {
            // Assuming CSVQuestions is used within PopUpQuiz, or it is the quiz itself
//            new CSVQuestions().setVisible(true); // If CSVQuestions creates its own window

            new PopUpQuiz(user).display(); // If PopUpQuiz wraps around the quiz functionality
        }
        else if (cellType == CellType.MARKET) {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);

                // Correcting the way PopUpMarket is displayed; no need for a new JFrame here
                PopUpMarket market = new PopUpMarket(user, (JFrame) SwingUtilities.getWindowAncestor(this));
                market.display();
        }

    }

}
