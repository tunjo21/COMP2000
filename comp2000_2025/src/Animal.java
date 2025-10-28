public abstract class Animal extends Actor implements Movable {
    protected int scoreValue ;

    public Animal(Cell inLoc, int scoreValue) {
        this.loc = inLoc;
        this.scoreValue = scoreValue;
    }

    @Override
    public void moveTowards(Cell target, Grid grid) {
        int dx = Integer.compare(target.col, loc.col);
        int dy = Integer.compare(target.row, loc.row);
        int newCol = loc.col - 'A' + dx;
        int newRow = loc.row + dy;

        // stay inside grid bounds
        newCol = Math.max(0, Math.min(grid.cells.length - 1, newCol));
        newRow = Math.max(0, Math.min(grid.cells[0].length - 1, newRow));

        loc = grid.cellAtColRow(newCol, newRow).get();
        updateDisplay();
    }

    @Override
    public abstract void move(Grid grid);

    protected abstract void updateDisplay();
}
