# Catch the Monster

## Overview
This project extends the Week 5 Grid game into an interactive chase game.  
The player controls multiple animals (**Cat, Dog, Bird**) and can click on the grid to move them toward a randomly moving **Monster**. Points are gained when an animal reaches the Monster.  

---

## Gameplay
- Click on a cell to move the nearest animal toward the Monster.
- Score 10 points each time an animal reaches the Monster.
- Monster moves randomly around the grid.
- If the player is struggling (score < 50 after 1 minute), a Fish helper appears.
- Fish disappears once the score reaches 100.
- The current score and helper messages are displayed on the right-hand side of the canvas.

---

## How to Compile and Run
1. Unzip the project folder.
2. Open in your preferred Java IDE (VS Code, Eclipse, IntelliJ, etc.) or compile/run via command line.
3. Run Main.java.
4. Follow on-screen instructions to click animals toward the monster, score points, and enjoy the Fish helper feature after 60 seconds if needed.

---

## Inheritance
* `Actor` is the base class for all drawable game characters.
* `Animal` extends `Actor` to represent movable animals (Cat, Bird, Dog and Fish).
*  Each specific animal (`Cat`, `Dog`, `Bird`, `Fish`) overrides `updateDisplay()` for its unique shape but reuses the `paint(Graphics g)` method from `Actor`.
  
**Benefit:** Avoids code duplication and allows consistent rendering of all animals.

---

## Interfaces
The `Movable` interface defines two methods:
* `move(Grid grid)` → autonomous movement.
* `moveTowards(Cell target, Grid grid)` → directed movement toward a target.

Both `Animal` and `Monster` implement `Movable` , allowing:
* Polymorphic handling of all moving characters.
* The `Stage` class can move any `Movable` object without knowing its type.

**Benefit:** Supports flexible design and uniform handling of moving characters.

---

## Generics
* `List<Animal>` in `Stage` stores all animals:

```bash
List<Animal> animals = new ArrayList<>();
```

*`Optional<Cell>` in `Grid` safely returns a cell or empty:

```bash
public Optional<Cell> cellAtColRow(int col, int row)
```

*`List<Polygon>` in `Actor` stores the shapes for each character:

```bash
List<Polygon> display;
```

**Benefit:** Ensures type safety and avoids runtime errors and Using `List<Animal>` allows the `Stage` to manage any future animal type without modifying existing code.

---

## Unique Features
1. Helper Fish: Appears automatically if score < 50, disappears after score reaches 100.
2. Message System: Displays notifications under the score when Fish joins or leaves.
3. Collision Handling: Score increments only once per collision.
4. Random Monster Movement: Monster moves randomly across the grid.

---

## Controls
* Mouse Click: Moves the nearest animal toward the clicked location and ultimately toward the Monster.

---

## Requirements
* Java 11 or Java 21

Enjoy the game!


  