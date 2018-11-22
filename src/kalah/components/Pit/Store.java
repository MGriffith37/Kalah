package kalah.components.Pit;

import kalah.components.MoveOutcome;
import kalah.components.Player;

public class Store extends Pit implements PitInterface{

    public Store(Player associatedPlayer){
        super(associatedPlayer);
        _seedCount = 0;
    }

    public boolean sow(Player currentPlayer) {
        return sowStore(currentPlayer);
    }
    public int empty(){
        return emptyStore();
    }

    @Override
    public MoveOutcome sowFinalSeed(Player player) {
        if (this.sow(player)) {
            return MoveOutcome.RepeatTurn;
        }
        return MoveOutcome.SowNextHouse;
    }

    private boolean sowStore(Player currentPlayer){
        if(currentPlayer == _player){
            _seedCount++;
            return true;
        }
        return false;
    }
    private int emptyStore(){
        return getSeedAmount();
    }
}
