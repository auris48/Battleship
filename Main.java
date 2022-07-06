package battleship;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int turn = 0;

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        System.out.println(player1.getPlayerName() + ", place your ships to the game field\n");
        player1.setUpShips();
        passMoveMessage();
        System.out.println(player2.getPlayerName() + ", place your ships to the game field\n");
        player2.setUpShips();
        passMoveMessage();

        System.out.println("The game starts!");

        boolean running = true;
        while (running) {

            if (turn == 0) {

                System.out.println(player1.getPlayerName() + ", it's your turn!\n");
                player2.getBoard().printField(true, false);
                System.out.println("----------------------");
                player1.getBoard().printField(false, false);


                if (player1.processInput(scanner.nextLine())) {
                    player2.getBoard().makeShot(player1.getShotY(), player1.getShotX());
                } else {
                    System.out.println("Bad input");
                    continue;
                }
            } else {
                System.out.println("\n" + player2.getPlayerName() + ", it's your turn!\n");
                player1.getBoard().printField(true, false);
                System.out.println("---------------------");
                player2.getBoard().printField(false, false);


                if (player2.processInput(scanner.nextLine())) {
                    player1.getBoard().makeShot(player2.getShotY(), player2.getShotX());
                } else {
                    System.out.println("Bad input");
                    continue;
                }

            }

            running = player1.getBoard().checkWin() || player2.getBoard().checkWin() ? false : true;

            if (running){
                passMoveMessage();
            }

            turn = turn == 0 ? 1 : 0;

        }

        System.out.println("You sunk the last ship. You won. Congratulations!");

    }

    public static void passMoveMessage() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println("...");
    }
}
