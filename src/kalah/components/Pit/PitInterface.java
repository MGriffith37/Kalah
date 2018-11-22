package kalah.components.Pit;

import kalah.components.MoveOutcome;
import kalah.components.Player;

public interface PitInterface {
    boolean isEmpty();
    int getSeedAmount();
    int empty();
    boolean sow(Player player);
    void store(int i);
    Player getPlayer();
    MoveOutcome sowFinalSeed(Player player);
}
