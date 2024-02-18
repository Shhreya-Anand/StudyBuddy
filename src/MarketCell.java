public class MarketCell extends Cell {
    // Constructor
    public MarketCell(int row, int col) {
        super(row, col, CellType.MARKET);
    }

    @Override
    public void playerEntryTrigger(Player player) {
        // Implement market logic here
    }
}