package kalah.components.Pit;

import kalah.components.Player;

public class Pit{
    protected Player _player;
    protected int _seedCount;

    public Pit(Player associatedPlayer){
        this._player = associatedPlayer;
    }

    public boolean isEmpty(){
        if(_seedCount == 0){
            return true;
        }
        return false;
    }

    public void store(int amount){
        _seedCount += amount;
    }
    public int getSeedAmount(){
        return _seedCount;
    }
    public Player getPlayer(){
        return _player;
    }
}
