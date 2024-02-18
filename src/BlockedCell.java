
public class BlockedCell extends Cell {
    // Constructor
    public BlockedCell(int row, int col) {
        super(row, col, CellType.BLOCKED);
    }

    @Override
    public void playerEntryTrigger(Player player) {
        // Implement blocked logic here
    }
}