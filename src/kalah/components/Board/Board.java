package kalah.components.Board;

import kalah.components.Pit.PitInterface;
import kalah.components.Player;
import kalah.exceptions.InvalidTurnException;
import kalah.components.MoveOutcome;

public class Board {

    private PitInterface[] _pits;

    protected void injectPits(PitInterface[] pits){
        _pits = pits;
    }

    public boolean isInPlay(Player currentPlayer) {
        for(int i = 0; i < (_pits.length/2) -1; i++){
            if(!_pits[i].isEmpty()){
                break;
            }
            if(i == _pits.length/2 -2 && currentPlayer == Player.PLAYER1){
                return false;
            }
        }
        for(int i = _pits.length/2; i < _pits.length-1; i++){
            if(!_pits[i].isEmpty()) {
                return true;
            }
            if(i == _pits.length-2 && currentPlayer == Player.PLAYER2){
                return false;
            }
        }
        return true;
    }

    public int[] getBoardState(){
        int[] simplePits = new int[_pits.length];
        for(int i = 0; i < _pits.length; i++){
            simplePits[i] = _pits[i].getSeedAmount();
        }
        return simplePits;
    }

    public MoveOutcome makeMove(Player currentPlayer, int pitNo) throws InvalidTurnException {
       int pitIndex;
        if(currentPlayer.equals(Player.PLAYER1)){
            pitIndex = pitNo-1;
        }else{
            pitIndex = pitNo + _pits.length/2 -1;
        }
        if(_pits[pitIndex].isEmpty()){
            throw new InvalidTurnException();
        }
        int seedCount = _pits[pitIndex].empty();

        for(int i = 1; i <= seedCount; i++){

            int currentIndex = (pitIndex+i) % _pits.length;
            if(i == seedCount){
                MoveOutcome nextMove = _pits[currentIndex].sowFinalSeed(currentPlayer);
                if(nextMove == MoveOutcome.Capture) {
                    capture(currentIndex);
                    nextMove = MoveOutcome.Normal;
                }else if(nextMove == MoveOutcome.SowNextHouse){
                    _pits[(currentIndex+1)%14].sow(currentPlayer);
                    nextMove = MoveOutcome.Normal;
                }
                return nextMove;
            }

            if(!_pits[currentIndex].sow(currentPlayer)){
                seedCount++;
            }
        }
        return MoveOutcome.Normal;
    }

    private void capture(int pitIndex) {
        int oppPitIndex = determineOppositePit(pitIndex);
        if(_pits[oppPitIndex].isEmpty()){
            return;
        }
        int seedCount = _pits[pitIndex].empty() + _pits[oppPitIndex].empty();

        if(oppPitIndex < _pits.length/2 - 1){
            _pits[_pits.length-1].store(seedCount);
        }else {
            _pits[_pits.length/2 -1].store(seedCount);
        }
    }

    private int determineOppositePit(int index){
        return _pits.length-2 - index;
    }

    public int getTotal(Player player) {
        int sum = 0;
        switch(player){
            case PLAYER1:
                for(int i = 0; i < _pits.length/2; i++) {
                    sum += _pits[i].empty();
                }
                break;
            case PLAYER2:
                for(int i = _pits.length/2; i < _pits.length; i++) {
                    sum += _pits[i].empty();
                }
                break;
        }
        return sum;
    }
}
