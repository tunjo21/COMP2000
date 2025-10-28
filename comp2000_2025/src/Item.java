public class Item {
    int points;
    Cell loc;

public Item (Cell loc, int points){
    this.loc = loc; //place item on cell
    this.points = points; //set points
}

    public int getPoints() {
        return points;
    }

    public Cell getLocation() {
        return loc;
    }
}
