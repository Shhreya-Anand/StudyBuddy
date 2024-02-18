import java.util.ArrayList;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    // Example method to interact with cells
    public void moveTo(Cell cell) {
        System.out.println(name + " moves to cell (" + cell.getRow() + ", " + cell.getCol() + ")");
        cell.playerEntryTrigger(this);
    }

    // Get player's name
    public String getName() {
        return name;
    }
}
