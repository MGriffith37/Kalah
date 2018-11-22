package kalah.components.Board;


import kalah.components.Pit.House;
import kalah.components.Pit.PitInterface;
import kalah.components.Pit.Store;
import kalah.components.Player;

/*TODO
    Split up board into three components
    One based on initialising it
    One based on operating on the board
    One based on managing the state of the board


    change pits to be an interface that is open to extension and closed to modification
 */
public class BoardGenerator {
    private int _totalNumberPits;
    private int _startingPitSeeds;

    public BoardGenerator(){
        _totalNumberPits = 14;
        _startingPitSeeds = 4;
    }

    public BoardGenerator(int totalPits, int startingSeeds){
        _totalNumberPits = totalPits;
        _startingPitSeeds = startingSeeds;
    }

    public Board generateBoard(){
        Board board = new Board();
        PitInterface[] pits = new PitInterface[_totalNumberPits];
        Player player = Player.PLAYER1;
        int halfwayInt = (_totalNumberPits/2) -1;
        for(int i = 0; i < halfwayInt; i++) {
            pits[i] = new House(player, i + 1, _startingPitSeeds);
        }
        pits[halfwayInt] = new Store(player);

        player = Player.PLAYER2;
        for(int i = halfwayInt+1; i < _totalNumberPits-1; i++) {
            pits[i] = new House(player, i-1, _startingPitSeeds);
        }
        pits[_totalNumberPits-1] = new Store(player);
        board.injectPits(pits);

        return board;
    }
}
