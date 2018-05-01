package kalah.components;

public class Store extends Pit {

    public Store(Player associatedPlayer){
        super(associatedPlayer);
        _seedCount = 0;
    }

    @Override
    public boolean sow(Player currentPlayer) {
        if(currentPlayer == _player){
            _seedCount++;
            return true;
        }
        return false;
    }

    public int empty(){
        return _seedCount;
    }
}
