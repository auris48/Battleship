package battleship;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Player {
    protected int fromX;
    protected int fromY;
    protected int toX;
    protected int toY ;
    protected int shotX;
    protected int shotY;
    private String playerName;
    private GameField board;

    public Player(String playerName) {
        this.playerName=playerName;
        board = new GameField();
    }

    public String getPlayerName() {
        return playerName;
    }

    public GameField getBoard() {
        return board;
    }

    public static void printShipRequest(int a) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", ShipType.values()[a].getShipName(),
                ShipType.values()[a].getSize());
    }

    public boolean processInput(String input) {

        List<String> letters = Arrays.asList("A", "B",
                "C", "D", "E", "F", "G", "H", "I", "J");

        if (input.contains(" ")) {
            String[] coordinates = input.split(" ");
            String fromCoord = coordinates[0];
            String toCoord = coordinates[1];

            fromY = letters.indexOf(String.valueOf(fromCoord.charAt(0)).toUpperCase(Locale.ROOT));
            fromX = Integer.parseInt(fromCoord.substring(1)) - 1;
            toY = letters.indexOf(String.valueOf(toCoord.charAt(0)).toUpperCase(Locale.ROOT));
            toX = Integer.parseInt(toCoord.substring(1)) - 1;
        } else {
            shotY = letters.indexOf(String.valueOf(input.charAt(0)).toUpperCase(Locale.ROOT));
            shotX = Integer.parseInt(input.substring(1)) - 1;

            if ((shotY < 0 || shotY > 9) || (shotX < 0 || shotX > 9)) {
                shotY = 0;
                shotX = 0;
                return false;
            }
        }
        return true;
    }

    public  void setUpShips() {
        board.printField(false, false);
        for (int i = 0; i < 5; i++) {
            printShipRequest(i);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            processInput(input);
            board.shipsCollection.add(new Ship(ShipType.values()[i], fromY, fromX, toY, toX));
            String placeShipResponse = board.placeShip(board.shipsCollection.get(i));

            if (placeShipResponse.equals("OK")) {
                board.printField(false, false);
            } else {
                board.shipsCollection.remove(board.shipsCollection.size() - 1);
                System.out.println(placeShipResponse);
                i--;
            }
        }

    }

    public int getShotY() {
        return shotY;
    }

    public int getShotX() {
        return shotX;
    }
}
