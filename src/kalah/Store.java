package kalah;

public class Store extends Pit {

    public Store(Player associatedPlayer){
        super(associatedPlayer);
        _seedCount = 0;
    }

    @Override
    public boolean increment(Player currentPlayer) {
        if(currentPlayer == _player){
            _seedCount++;
            return true;
        }
        return false;
    }
}
