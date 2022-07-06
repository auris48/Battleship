package battleship;


import java.util.ArrayList;

enum ShipType {
    AIRCARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private int size;
    private String shipName;

    ShipType(int size, String shipName) {
        this.size = size;
        this.shipName = shipName;
    }

    public String getShipName() {
        return shipName;
    }

    public int getSize() {
        return size;
    }
}

enum Orientation {VERTICAL, HORIZONTAL}

public class Ship {

    protected ShipType shipType;
    boolean isSunk;
    private Orientation orientation;
    private Coordinates coordinates;
    private ArrayList<Cell> cellsBelongingToShip = new ArrayList<>();

    public Ship(ShipType ship, int fromY, int fromX, int toY, int toX) {
        this.shipType = ship;
        setCoordinates(fromY, fromX, toY, toX);
        checkOrientation();

    }


    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getSize() {
        return shipType.getSize();
    }

    public void setCoordinates(int fromY, int fromX, int toY, int toX) {
        this.coordinates = new Coordinates(fromY, fromX, toY, toX);
    }


    public boolean checkOrientation() {
        if (coordinates.fromY == coordinates.toY && coordinates.fromX != coordinates.toX) {
            this.setOrientation(Orientation.HORIZONTAL);
        } else if (coordinates.fromY != coordinates.toY && coordinates.fromX == coordinates.toX) {
            this.setOrientation(Orientation.VERTICAL);
        } else {
            return false;
        }
        return true;
    }

    public boolean checkSize() {
        return getCellsBelongingToShip().size() == getSize() ? true : false;
    }

    public void setCellsBelongingToThisShip(Cell[][] cellsArray) {
        int from = 0;
        int to = 0;
        int anchor = 0;

        if (this.getOrientation() == Orientation.HORIZONTAL) {
            from = coordinates.fromX < coordinates.toX ?
                    coordinates.fromX : coordinates.toX;
            to = coordinates.toX > coordinates.fromX ?
                    coordinates.toX : coordinates.fromX;
            anchor = coordinates.fromY;

        } else if (this.getOrientation() == Orientation.VERTICAL) {
            from = coordinates.fromY < coordinates.toY ?
                    coordinates.fromY : coordinates.toY;
            to = coordinates.toY > coordinates.fromY ?
                    coordinates.toY : coordinates.fromY;
            anchor = coordinates.fromX;
        }

        for (int i = from; i <= to; i++) {
            if (this.getOrientation() == Orientation.HORIZONTAL) {
                this.cellsBelongingToShip.add(cellsArray[anchor][i]);
            } else {
                this.cellsBelongingToShip.add(cellsArray[i][anchor]);
            }
        }

    }

    public boolean checkIfSunk() {

        for (Cell cell : cellsBelongingToShip) {
            if (!cell.isHit()) {
                return false;
            }
        }

        this.isSunk=true;
        return true;
    }

    public ArrayList<Cell> getCellsBelongingToShip() {
        return cellsBelongingToShip;
    }


    class Coordinates {

        protected int fromX;
        protected int fromY;
        protected int toX;
        protected int toY;

        public Coordinates(int fromY, int fromX, int toY, int toX) {

            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;

        }
    }

}
