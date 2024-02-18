public class Piece {
    private int row;
    private int col;
    // Assuming the piece symbol is just for console-based games, for Swing, we will draw it.

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }
}
