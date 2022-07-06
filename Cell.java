package battleship;

public class Cell {
    private int x;
    private int y;
    private boolean containsShip=false;
    private Ship ship;
    boolean isHit=false;



    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isContainsShip() {
        return containsShip;
    }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.setContainsShip(true);
        this.ship = ship;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
