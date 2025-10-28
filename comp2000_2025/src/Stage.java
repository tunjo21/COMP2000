import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.Font;

public class Stage {
    Grid grid;
    List<Animal> animals;
    Monster monster;
    int score = 0;
    long startTime;
    boolean fishAdded = false;
    String message = "";
    long messageTime = 0;

    public Stage() {
        grid = new Grid();
        animals = new ArrayList<>();
        animals.add(new Cat(grid.cellAtColRow(0,6).get()));
        animals.add(new Dog(grid.cellAtColRow(0,15).get()));
        animals.add(new Bird(grid.cellAtColRow(12,9).get()));
        monster = new Monster(grid.cellAtColRow(10,10).get());

        startTime = System.currentTimeMillis();
    }

    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
        for (Animal a : animals) a.paint(g);
        g.setColor(java.awt.Color.RED);
        g.fillRect(monster.loc.x, monster.loc.y, Cell.size, Cell.size);
        g.setColor(java.awt.Color.GRAY);
        g.fillRect(grid.cells[0].length *Cell.size + 20,30,295,100);
        g.setColor(java.awt.Color.PINK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: "+score, grid.cells[0].length * Cell.size + 20, 50);
        if (!message.isEmpty() && (System.currentTimeMillis() - messageTime < 5000)) {
            g.setColor(java.awt.Color.PINK);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString(message, grid.cells[0].length * Cell.size + 20, 100);
        }
      }

    public void update() {
      if (new java.util.Random().nextInt(80) == 0) {  
      monster.move(grid);
      monster.setScored(false);
      }
       long elapsed = (System.currentTimeMillis() - startTime) / 1000;
    if (elapsed >= 60 && score < 50 && !fishAdded) {
        animals.add(new Fish(grid.cellAtColRow(5, 5).get()));
        fishAdded = true;
        message = ("A Fish has joined the team to help you!");
        messageTime = System.currentTimeMillis();
    }

    if(score >=100) {
      for(int i = animals.size() - 1; i >= 0; i--) {
        Animal a = animals.get(i);
        if (a instanceof Fish) {
          animals.remove(i);

        }
      }
      if (fishAdded) {
        message = "Fish is saying bye bye!";
        messageTime = System.currentTimeMillis();
        fishAdded = false;
      }
    }

    }

    public void handleClick(Point p) {
        Optional<Cell> clicked = grid.cellAtPoint(p);
        if(clicked.isPresent()) {
          //find animal
            Animal nearest = animals.get(0);
            double minDist = Double.MAX_VALUE;
            for (Animal a : animals) {
                double d = p.distance(a.loc.x, a.loc.y);
                if(d < minDist) { 
                  minDist=d; 
                  nearest=a;
                }
            }

            nearest.moveTowards(monster.loc, grid);

           if (nearest.loc == monster.loc && !monster.isScored()) {
            score += nearest.scoreValue;
           // System.out.println("Score: " + score);
           // monster.setScored(true);
            
            // no drastic increase in score
            int randCol = (int)(Math.random() * grid.cells.length);
            int randRow = (int)(Math.random() * grid.cells[0].length);
            monster.loc = grid.cellAtColRow(randCol, randRow).get();
           // monster.updateDisplay();  
        }
    }
  }
}
