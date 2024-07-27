import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Welcome to Tic-Tac-Toe!");
        Scanner scanner = new Scanner(System.in);
        TicTacToeController controller = new TicTacToeController();

        int boardSize = 3;
        List<Player> participants = List.of(
                new Player("Alice", new Symbol('X'), PlayerType.HUMAN),
                new Bot("BotBob", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        boolean playAgain;
        do {
            TicTacToeGame game = controller.startGame(boardSize, participants);
            List<String> moveHistory = new ArrayList<>();
            while (game.getGameState().equals(GameState.IN_PROGRESS)) {
                controller.printBoard(game);
                controller.makeMove(game);
                moveHistory.add(game.getLastMove().toString());
            }

            if (!controller.checkState(game).equals(GameState.ENDED)) {
                game.setGameState(GameState.DRAW);
                System.out.println("The game is a DRAW");
            } else {
                controller.printBoard(game);
                System.out.println("Player " + controller.getWinner(game).getName() + " wins!");
            }

            System.out.println("Move History:");
            for (String move : moveHistory) {
                System.out.println(move);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing Tic-Tac-Toe!");
    }
}
