package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board.Board;
import kalah.components.Board.BoardGenerator;
import kalah.components.Board.BoardIO;
import kalah.components.MoveOutcome;
import kalah.components.Player;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		/** By default generates a board with 14 total pits and 4 starting seeds
		 *  Can generate a new board using the alternative constructor BoardGenerator(int totalPits, int startingSeedCount)
		 *
		 *  E.g. BoardGenerator bg = new BoardGenerator(18, 6)
		 *  Generates a board where each player has 8 houses and a store, and each house starts with 6 seeds
		 *  Note: totalPits must be an even number
		 */
		BoardGenerator bg = new BoardGenerator();

		Board gameBoard = bg.generateBoard();

		Player currentPlayer = Player.PLAYER1;

		BoardIO boardIO = new BoardIO(gameBoard, io);
		boardIO.printBoard();

		while (gameBoard.isInPlay(currentPlayer)) {

			int houseNumber = boardIO.readPlayerPit(currentPlayer);

			if (houseNumber < 0) {
				break;
			}

			MoveOutcome outcome;
			try {
				outcome = gameBoard.makeMove(currentPlayer, houseNumber);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				io.println("House is empty. Move again.");
				outcome = MoveOutcome.RepeatTurn;
			}

			switch (outcome) {
				case Normal:
					currentPlayer = (currentPlayer == Player.PLAYER1) ? Player.PLAYER2 : Player.PLAYER1;
					break;
				case RepeatTurn:
					break;
			}

			boardIO.printBoard();

		}

		boardIO.gameOver();
		boardIO.printBoard();

		if (!gameBoard.isInPlay(currentPlayer)) {
			boardIO.announceWinner();
		}
	}
}
