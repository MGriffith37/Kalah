package kalah.components;

import kalah.exceptions.InvalidTurnException;
import kalah.misc.MoveOutcome;

public class Board {

    private Pit[] _pits;

    public Board(){
        _pits = new Pit[14];
        initialise();
    }
    private void initialise() {
        Player player = Player.PLAYER1;
        for(int i = 0; i < 6; i++) {
            _pits[i] = new House(player, i + 1);
        }
        _pits[6] = new Store(player);

        player = Player.PLAYER2;
        for(int i = 7; i < 13; i++) {
            _pits[i] = new House(player, i-1);
        }
        _pits[13] = new Store(player);

    }

    public boolean isInPlay(Player currentPlayer) {
        for(int i = 0; i < 6; i++){
            if(!_pits[i].isEmpty()){
                break;
            }
            if(i == 5 && currentPlayer == Player.PLAYER1){
                return false;
            }
        }
        for(int i = 7; i < 13; i++){
            if(!_pits[i].isEmpty()) {
                return true;
            }
            if(i == 12 && currentPlayer == Player.PLAYER2){
                return false;
            }
        }
        return true;
    }

    public int[] getBoardState(){
        int[] simplePits = new int[14];
        for(int i = 0; i < 14; i++){
            simplePits[i] = _pits[i].getSeedAmount();
        }
        return simplePits;
    }

    public MoveOutcome makeMove(Player currentPlayer, int pitNo) throws InvalidTurnException {
       int pitIndex;
        if(currentPlayer.equals(Player.PLAYER1)){
            pitIndex = pitNo-1;
        }else{
            pitIndex = pitNo + 6;
        }
        if(_pits[pitIndex].isEmpty()){
            throw new InvalidTurnException();
        }
        int seedCount = _pits[pitIndex].empty();

        for(int i = 1; i <= seedCount; i++){

            if(i == seedCount){
                if(_pits[(pitIndex+i)%14].getClass().equals(Store.class)){
                    if(_pits[(pitIndex+i)%14].sow(currentPlayer)){
                        return MoveOutcome.RepeatTurn;
                    }
                } else if(_pits[(pitIndex+i)%14].isEmpty() && _pits[(pitIndex+i)%14].getClass().equals(House.class) && _pits[(pitIndex+i) % 14].getPlayer() == currentPlayer) {
                    _pits[(pitIndex+i)%14].sow(currentPlayer);

                    capture((pitIndex+i)%14);
                    return MoveOutcome.Normal;
                }
            }

            if(!_pits[(pitIndex+i)%14].sow(currentPlayer)){
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

        if(oppPitIndex < 6){
            _pits[13].store(seedCount);
        }else {
            _pits[6].store(seedCount);
        }
    }

    private int determineOppositePit(int index){
        return 12 - index;
    }

    public int getTotal(Player player) {
        int sum = 0;
        switch(player){
            case PLAYER1:
                for(int i = 0; i < 7; i++) {
                    sum += _pits[i].getSeedAmount();
                }
                break;
            case PLAYER2:
                for(int i = 7; i < 14; i++) {
                    sum += _pits[i].getSeedAmount();
                }
                break;
        }
        return sum;
    }
}
