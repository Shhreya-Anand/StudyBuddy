public class SimpleCell extends Cell {

    public SimpleCell(int row, int col, CellType type) {
        super(row, col, type);
    }

    @Override
    public void playerEntryTrigger(Player player) {
        System.out.println(player.getName() + " has entered a " + this.type + " cell at (" + this.row + ", " + this.col + ")");
        // Implement specific logic based on cell type
    }
}
