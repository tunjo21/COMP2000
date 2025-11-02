Catch the Monster and Enjoy the Weather
====================

### Overview

**Catch the Monster** is an interactive Java grid-based game that combines fun gameplay with live weather integration and object-oriented design principles.\
Players control multiple animals **Cat**, **Dog**, and **Bird** to chase down a randomly moving **Monster**. Each time an animal catches the monster, the player earns points. The environment dynamically reacts to **real-time weather data** fetched from an external API.

* * * * *

### Gameplay Features

-   **Click anywhere** on the grid to make the **nearest animal** move toward the Monster.

-   **Score points** whenever an animal reaches the Monster.

-   The **Monster moves randomly** every few seconds.

-   After **1 minute**, if the player's score is below 50, a **Fish helper** appears to assist.

-   The **Fish disappears** once the score reaches 100.

-   **Dynamic weather system** --- affects animal behavior:

    -   **Rainy** → Cat stops moving.

    -   **Windy** → Bird stops moving.

    -   **Hot** → Dog stops moving.

    -   **Clear** → All move normally.

    -   Fish is **not affected** by weather.

-  Weather data is fetched from a **real HTTP API** but also simulated locally for smoother gameplay.

-  Game ends after **3 minutes**; players can **click anywhere to restart**.

* * * * *

### How to Run

1.  Open the project in **VS Code** or any Java IDE.

2.  Run the file **`Main.java`** (the entry point of the program).

3.  Use your **mouse** to control the animals and chase the monster.

4.  Watch how the grid and animals react dynamically to the **weather changes**!

* * * * *

#### Inheritance and Polymorphism

-   `Actor` → base class for all drawable characters.

-   `Animal` extends `Actor` and adds behavior for movement.

-   Subclasses like `Cat`, `Dog`, `Bird`, and `Fish` override visuals and movement logic.

-   The `Stage` class treats all animals **polymorphically** it doesn't need to know whether it's a Cat, Dog, or Bird.

This design makes the game easily extensible. 

* * * * *

### Design Pattern --- **Strategy Pattern**

The weather system uses the **Strategy Design Pattern**, separating the logic for how the grid color reacts to weather conditions.

#### Key classes:

-   `WeatherStrategy` → defines the interface:

```bash 
    public interface WeatherStrategy {
        Color getColor(String weather);
    }
```

-   `SmartWeatherStrategy` → implements the strategy:

```bash 
    public Color getColor(String weather) {
        if ("Rainy".equals(weather)) return Color.BLUE;
        else if ("Windy".equals(weather)) return Color.GRAY;
        else if ("Hot".equals(weather)) return Color.ORANGE;
        else return Color.WHITE;
    }
```

-   The `Stage` class uses this strategy dynamically:

```bash
    c.setColor(strategy.getColor(currentWeather));
```

**Benefit:** This pattern allows the grid’s color logic to change without modifying the rest of the program, improving modularity, reusability, and maintainability. It clearly demonstrates understanding of good software design principles.

* * * * *

### **Lambdas & Streams**

1.  **Lambda in List filtering:**

```bash
    animals.removeIf(a -> a instanceof Fish);
```
  Removes fish using a clean, functional approach instead of a manual loop.

2.  **Stream for grid updates:**

```bash
    java.util.Arrays.stream(grid.cells)
        .flatMap(java.util.Arrays::stream)
        .forEach(c -> c.setColor(strategy.getColor(currentWeather)));
```

**Benefit:** Replaces nested loops with a concise, functional operation that updates every grid cell's color.

* * * * *

### Weather Integration (with Backup Simulation)

The game connects to:

`http://13.238.167.130/weather` via the `WeatherFetcher` class, which continuously reads data using **Java's HttpClient** and updates a shared `WeatherData` object.

When the connection is slow or unavailable, the code **simulates random weather updates**, ensuring smooth gameplay. 

**Benefit:** The game remains reliable and enjoyable even if the real API fails — showing robustness and good defensive programming practices.

* * * * *

### Example of Key Method --- `update()`

```bash
if (elapsed >= 60 && score < 50 && !fishAdded) {
    animals.add(new Fish(grid.cellAtColRow(5, 5).get()));
    fishAdded = true;
    message = "A Fish has joined the team to help you!";
    messageTime = System.currentTimeMillis();
}

if (score >= 100 && fishAdded) {
    animals.removeIf(a -> a instanceof Fish);
    fishAdded = false;
    message = "Fish is saying bye bye!";
    messageTime = System.currentTimeMillis();
}
```

This method showcases:

-   **Conditional logic** (based on score and time)

-   **Lambda usage** (`removeIf`)

-   **State management** (helper tracking and message timing)

## Unique Features
1. Helper Fish: Appears automatically if score < 50, disappears after score reaches 100.
2. Message System: Displays notifications under the score when Fish joins or leaves.
3. Collision Handling: Score increments only once per collision.
4. Random Monster Movement: Monster moves randomly across the grid.
5. Animal and grid Response: Animals move depending on the weather and the grid color changes according to the weather too.

---

## Controls
* Mouse Click: Moves the nearest animal toward the clicked location and ultimately toward the Monster.

---

## Requirements
* Java 11 or Java 21

Enjoy the game!

