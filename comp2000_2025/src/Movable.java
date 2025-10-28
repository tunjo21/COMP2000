public interface Movable {
    //void move(int dx, int dy, Grid grid);
      void moveTowards(Cell target, Grid grid);
      void move(Grid grid);
}
