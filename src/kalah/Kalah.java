package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {

		Board gameBoard = new Board();
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
