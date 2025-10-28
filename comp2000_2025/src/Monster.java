import java.util.Random;

public class Monster implements Movable {
    public Cell loc;
    public boolean scored = false;
    private Random rand = new Random();

    public Monster(Cell start) { 
        this.loc = start; 
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean val) {
        scored = val;
    }

    @Override
    public void move(Grid grid) {
        int dx = rand.nextInt(3) - 1;  // -1, 0, or 1
        int dy = rand.nextInt(3) - 1;

        int newCol = loc.col - 'A' + dx;
        int newRow = loc.row + dy;

        // stay inside grid bounds
        newCol = Math.max(0, Math.min(grid.cells[0].length - 1, newCol));
        newRow = Math.max(0, Math.min(grid.cells.length - 1, newRow));

        loc = grid.cellAtColRow(newCol, newRow).get();

        scored = false;
    }

    @Override
    public void moveTowards(Cell target, Grid grid) {
        // Monsters just wander randomly
        // but implement this to satisfy the interface
        move(grid); 
    }
}
