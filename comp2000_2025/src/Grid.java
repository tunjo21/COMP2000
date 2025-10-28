import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class Grid {
    Cell[][] cells = new Cell[20][20];

  public Grid() {
    for (int row = 0; row < cells.length; row++) {
        for (int col = 0; col < cells[row].length; col++) {
            cells[row][col] = new Cell(colToLabel(col), row,
                                       10 + Cell.size * col,
                                       10 + Cell.size * row);
        }
    }
}

    private char colToLabel(int col) {
        return (char) (col + 'A');
    }

    public void paint(Graphics g, Point mousePos) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
    }

   public Optional<Cell> cellAtColRow(int col, int row) {
    if (row >= 0 && row < cells.length && col >= 0 && col < cells[row].length) {
        return Optional.of(cells[row][col]);
    } else {
        return Optional.empty();
    }
  }

    public Optional<Cell> cellAtPoint(Point p) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].contains(p)) {
                    return Optional.of(cells[i][j]);
                }
            }
        }
        return Optional.empty();
    }
}
