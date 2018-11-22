package kalah.components.Board;


import com.qualitascorpus.testsupport.IO;
import kalah.components.Player;

public class BoardIO {
    private Board _board;
    private IO _io;

    public BoardIO(Board board, IO io){
        _board = board;
        _io = io;
    }

    public void printBoard(){
        int[] currentBoardState = _board.getBoardState();
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        _io.println(String.format("| P2 | 6[%2s] | 5[%2s] | 4[%2s] | 3[%2s] | 2[%2s] | 1[%2s] | %2s |",
                currentBoardState[12], currentBoardState[11], currentBoardState[10], currentBoardState[9], currentBoardState[8], currentBoardState[7], currentBoardState[6]
        ));
        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        _io.println(String.format("| %2s | 1[%2s] | 2[%2s] | 3[%2s] | 4[%2s] | 5[%2s] | 6[%2s] | P1 |",
                currentBoardState[13], currentBoardState[0], currentBoardState[1], currentBoardState[2], currentBoardState[3], currentBoardState[4], currentBoardState[5]
        ));
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    public int readPlayerPit(Player currentPlayer){
       return  _io.readInteger(currentPlayer.id() + "'s turn - Specify house number or 'q' to quit: ", 1, 6, -1, "q");
    }

    public void gameOver(){
        _io.println("Game over");
    }

    public void announceWinner(){
        int p1Sum = _board.getTotal(Player.PLAYER1);
        int p2Sum = _board.getTotal(Player.PLAYER2);
        _io.println("\tplayer 1:" + p1Sum);
        _io.println("\tplayer 2:" + p2Sum);

        if (p1Sum == p2Sum) {
            _io.println("A tie!");
        } else {
            _io.println(p1Sum > p2Sum ? "Player 1 wins!" : "Player 2 wins!");
        }
    }

}
