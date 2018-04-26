package kalah;

public enum  Player {
    Player1 ( "Player 1"),
    Player2 ("Player 2");

    private final String name;

    Player(String name) {
        this.name = name;
    }

    private String name(){
        return name;
    }
}
