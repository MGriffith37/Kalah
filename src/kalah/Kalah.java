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
		int[] currentBoardState = gameBoard.getBoardState();

		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println(String.format("| P2 | 6[%2s] | 5[%2s] | 4[%2s] | 3[%2s] | 2[%2s] | 1[%2s] | %2s |",
				currentBoardState[12], currentBoardState[11],currentBoardState[10], currentBoardState[9], currentBoardState[8], currentBoardState[7], currentBoardState[6]
		));
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println(String.format("| %2s | 1[%2s] | 2[%2s] | 3[%2s] | 4[%2s] | 5[%2s] | 6[%2s] | P1 |",
				currentBoardState[13], currentBoardState[0],currentBoardState[1], currentBoardState[2], currentBoardState[3], currentBoardState[4], currentBoardState[5]
		));
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");

		while(gameBoard.isInPlay(currentPlayer)){



			int houseNumber = io.readInteger(currentPlayer.id() + "'s turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");

			if(houseNumber<0){
				break;
			}
			MoveOutcome outcome = MoveOutcome.Normal;
			try{
				outcome = gameBoard.makeMove(currentPlayer,  houseNumber);
			} catch (Exception e){
				io.println("House is empty. Move again.");
				outcome = MoveOutcome.RepeatTurn;
			}
			switch(outcome){
				case Normal:
					currentPlayer = (currentPlayer == Player.PLAYER1) ? Player.PLAYER2 : Player.PLAYER1;
					break;
				case RepeatTurn:
					break;
			}
			currentBoardState = gameBoard.getBoardState();

			io.println("+----+-------+-------+-------+-------+-------+-------+----+");
			io.println(String.format("| P2 | 6[%2s] | 5[%2s] | 4[%2s] | 3[%2s] | 2[%2s] | 1[%2s] | %2s |",
					currentBoardState[12], currentBoardState[11],currentBoardState[10], currentBoardState[9], currentBoardState[8], currentBoardState[7], currentBoardState[6]
			));
			io.println("|    |-------+-------+-------+-------+-------+-------|    |");
			io.println(String.format("| %2s | 1[%2s] | 2[%2s] | 3[%2s] | 4[%2s] | 5[%2s] | 6[%2s] | P1 |",
					currentBoardState[13], currentBoardState[0],currentBoardState[1], currentBoardState[2], currentBoardState[3], currentBoardState[4], currentBoardState[5]
			));
			io.println("+----+-------+-------+-------+-------+-------+-------+----+");

		}

		io.println("Game over");

		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println(String.format("| P2 | 6[%2s] | 5[%2s] | 4[%2s] | 3[%2s] | 2[%2s] | 1[%2s] | %2s |",
				currentBoardState[12], currentBoardState[11],currentBoardState[10], currentBoardState[9], currentBoardState[8], currentBoardState[7], currentBoardState[6]
		));
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println(String.format("| %2s | 1[%2s] | 2[%2s] | 3[%2s] | 4[%2s] | 5[%2s] | 6[%2s] | P1 |",
				currentBoardState[13], currentBoardState[0],currentBoardState[1], currentBoardState[2], currentBoardState[3], currentBoardState[4], currentBoardState[5]
		));
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");

		int p1Sum = gameBoard.getTotal(Player.PLAYER1);
		int p2Sum = gameBoard.getTotal(Player.PLAYER2);
		io.println( "\tplayer 1:" + p1Sum);
		io.println("\tplayer 2:"+ p2Sum);
		if(p1Sum == p2Sum){
			io.println("A tie!");
		}else {
			io.println(p1Sum > p2Sum ? "Player 1 wins!" : "Player 2 wins!");
		}
	}

}
