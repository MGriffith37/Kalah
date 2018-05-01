package kalah.components;

public class House extends Pit{
    int _id;

    public House(Player associatedPlayer, int id){
        super(associatedPlayer);
        _id = id;
        _seedCount = 4;
    }

    @Override
    public boolean sow(Player currentPlayer) {
        _seedCount++;
        return true;
    }

    @Override
    public int empty(){
        int seedsBeforeEmptying = _seedCount;
        _seedCount = 0;
        return seedsBeforeEmptying;
    }
}
