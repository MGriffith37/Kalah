package kalah.components.Pit;

import kalah.components.MoveOutcome;
import kalah.components.Player;

public class House extends Pit implements PitInterface{
    int _id;

    public House(Player associatedPlayer, int id, int startingSeeds){
        super(associatedPlayer);
        _id = id;
        _seedCount = startingSeeds;
    }

    public boolean sow(Player currentPlayer) {
        return sowHouse();
    }
    public int empty(){
        return emptyHouse();
    }

    @Override
    public MoveOutcome sowFinalSeed(Player player) {
        if(this.isEmpty() && _player == player){
            this.sow(player);
            return MoveOutcome.Capture;
        }else{
            this.sow(player);
            return MoveOutcome.Normal;
        }
    }

    private boolean sowHouse(){
       _seedCount++;
       return true;
    }
    private int emptyHouse(){
        int seedsBeforeEmptying = _seedCount;
        _seedCount = 0;
        return seedsBeforeEmptying;
    }
}
