package kalah;

public abstract class Pit {
    Player _player;
    int _seedCount;


    public Pit(Player associatedPlayer){
        this._player = associatedPlayer;
    }

    public abstract boolean increment(Player currentPlayer);

    public boolean isEmpty(){
        if(_seedCount == 0){
            return true;
        }
        return false;
    }
}
