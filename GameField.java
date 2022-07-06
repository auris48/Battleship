package battleship;

import java.util.ArrayList;

public class GameField  {

    ArrayList<Ship> shipsCollection = new ArrayList<>();

    private Cell[][] cellsArray = new Cell[10][10];

    public GameField() {
        initializeField();
    }

    public void setShipOnGrid(Ship ship) {
        for (Cell cell : ship.getCellsBelongingToShip()) {
            cell.setShip(ship);
        }
    }

    public void initializeField() {
        for (int i = 0; i < cellsArray.length; i++) {
            for (int j = 0; j < cellsArray[i].length; j++) {
                cellsArray[i][j] = new Cell(i, j);

            }
        }
    }

    public void printField(boolean fogOfWar, boolean empty) {
        //System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char yLetter = 65;
        for (int i = 0; i < cellsArray.length; i++) {
            System.out.print(yLetter + " ");
            for (int j = 0; j < cellsArray[i].length; j++) {
                if (!cellsArray[i][j].isHit && !cellsArray[i][j].isContainsShip() || (fogOfWar && !cellsArray[i][j].isHit()) || empty) {
                    System.out.print("~ ");
                } else if (cellsArray[i][j].isContainsShip() && !cellsArray[i][j].isHit() && !fogOfWar) {
                    System.out.print("O ");
                } else if (cellsArray[i][j].isHit() && cellsArray[i][j].isContainsShip()) {
                    System.out.print("X ");
                } else if (cellsArray[i][j].isHit() && !cellsArray[i][j].isContainsShip()) {
                    System.out.print("M ");
                }
            }
            System.out.println();
            yLetter++;
        }
    }

    public boolean checkCollision(Ship ship) {

        ArrayList<Cell> cells = ship.getCellsBelongingToShip();

        for (Cell cell : cells) {
            if (cell.getY() > 0) {
                for (int k = -1; k < 2; k++) {
                    if (cell.getX() + k > 9 || cell.getX() + k < 0) continue;
                    if (cellsArray[cell.getY() - 1][cell.getX() + k].isContainsShip()) {
                        return false;
                    }
                }
            }

            for (int k = -1; k < 2; k++) {
                if (cell.getX() + k > 9 || cell.getX() + k < 0) continue;
                if (cellsArray[cell.getY()][cell.getX() + k].isContainsShip()) {
                    return false;
                }
            }

            if (cell.getY() < 9) {
                for (int k = -1; k < 2; k++) {
                    if (cell.getX() + k > 9 || cell.getX() + k < 0) continue;
                    if (cellsArray[cell.getY() + 1][cell.getX() + k].isContainsShip()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public String placeShip(Ship ship) {

        boolean validOrientation = ship.checkOrientation();
        ship.setCellsBelongingToThisShip(cellsArray);
        boolean validSize = ship.checkSize();
        boolean noCollision = checkCollision(ship);

        if (validOrientation && validSize && noCollision) {
            setShipOnGrid(ship);
        } else {
            if (!validOrientation) {
                return "Error! Wrong ship location! Try again:";
            } else if (!validSize) {
                StringBuilder wrongSizeMessage = new StringBuilder("Error! Wrong length of the ! Try again");
                wrongSizeMessage.insert(27, ship.shipType.getShipName());
                return wrongSizeMessage.toString();
            } else if (!noCollision) {
                return "Error! You placed it too close to another one. Try again:";
            }
        }

        return "OK";
    }

    public boolean checkWin() {
        for (Ship ship : shipsCollection) {
            if (!ship.checkIfSunk()) {
                return false;
            }
        }
        return true;
    }

    public void makeShot(int y, int x) {
        cellsArray[y][x].setHit(true);
        String message="";

        if (cellsArray[y][x].isContainsShip()){
            if (cellsArray[y][x].getShip().checkIfSunk()){
                message="You sank a ship! Specify a new target: ";
            } else {
                message="You hit a ship! Try again:";
            }
        } else {
            message="You missed!";
        }

        printField(true, false);
        System.out.println(message+"\n");
    }

}
