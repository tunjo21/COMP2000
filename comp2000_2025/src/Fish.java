import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Fish extends Animal {
    public Fish(Cell inLoc) {
        super(inLoc, 10);
        color = Color.CYAN;
        updateDisplay();
    }


    @Override
    public void move (Grid grid) {
    }

     @Override
    protected void updateDisplay() {
        display = new ArrayList<>();

        Polygon body = new Polygon();
        body.addPoint(loc.x + 5, loc.y + 15);
        body.addPoint(loc.x + 15, loc.y + 10);
        body.addPoint(loc.x + 25, loc.y + 15);
        body.addPoint(loc.x + 15, loc.y + 20);

        Polygon tail = new Polygon();
        tail.addPoint(loc.x + 25, loc.y + 12);
        tail.addPoint(loc.x + 30, loc.y + 15);
        tail.addPoint(loc.x + 25, loc.y + 18);

        display.add(body);
        display.add(tail);
    }
}
