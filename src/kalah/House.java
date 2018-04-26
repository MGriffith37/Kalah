package kalah;

public class House extends Pit{
    int _id;

    public House(Player associatedPlayer, int id){
        super(associatedPlayer);
        _id = id;
        _seedCount = 4;
    }

    @Override
    public boolean increment(Player currentPlayer) {
        _seedCount++;
        return true;
    }
}
