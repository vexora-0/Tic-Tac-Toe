package com.example;

import com.example.controllers.GameController;
import com.example.exceptions.InvalidMoveException;
import com.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Bhargav");
        
        Scanner scanner = new Scanner(System.in);
        
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = List.of(
                new Player("Bhargav", new Symbol('X'), PlayerType.HUMAN),
            
                new Bot("hackclub", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        boolean playAgain;
        do {
            Game game = gameController.startGame(dimension, players);
            List<String> moveLog = new ArrayList<>(); // Track moves
            while (game.getGameState().equals(GameState.IN_PROGRESS)) {

                                gameController.printBoard(game);

                gameController.makeMove(game);


                                moveLog.add(game.getLastMove().toString());
            }

            // Check the game state
            if (!gameController.checkState(game).equals(GameState.ENDED)) {
                game.setGameState(GameState.DRAW);
                System.out.println("Game DRAW");
            } else {
                gameController.printBoard(game);
                System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
            }

            System.out.println("Move Log:");
            for (String move : moveLog) {
                System.out.println(move);
            }

             if (gameController.checkState(game).equals(GameState.ENDED)) {
                game.setGameState(GameState.DRAW);
                System.out.println("Game WON");
            } else {
                gameController.printBoard(game);
                System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing!");
    }
}
