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
		Player currentPlayer = Player.Player1;

		while(gameBoard.isInPlay()){

			int[] currentBoardState = gameBoard.getBoardState();
			//Use string.format() with %2s
			io.println("+----+-------+-------+-------+-------+-------+-------+----+");
			io.println("| P2 | 6[ "+currentBoardState[12]+"] | 5[ "+currentBoardState[11]+"] | 4[ "+currentBoardState[10]+"] | 3[ "+currentBoardState[9]+"] | 2[ "+currentBoardState[8]+"] | 1[ "+currentBoardState[7]+"] |  "+currentBoardState[6]+" |");
			io.println("|    |-------+-------+-------+-------+-------+-------|    |");
			io.println("|  "+currentBoardState[13]+" | 1[ "+currentBoardState[0]+"] | 2[ "+currentBoardState[1]+"] | 3[ "+currentBoardState[2]+"] | 4[ "+currentBoardState[3]+"] | 5[ "+currentBoardState[4]+"] | 6[ "+currentBoardState[5]+"] | P1 |");
			io.println("+----+-------+-------+-------+-------+-------+-------+----+");

			int houseNumber = io.readInteger(currentPlayer + "'s turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");
			io.println("");

			if(houseNumber<0){
				break;
			}

			try{
				gameBoard.distribute(currentPlayer,  houseNumber);
			} catch (Exception e){
				//invalid turn
			}

			if(currentPlayer.equals(Player.Player1)){
				currentPlayer = Player.Player2;
			}else{
				currentPlayer = Player.Player1;
			}

		}

	}

}
