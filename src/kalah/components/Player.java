package kalah.components;

public enum  Player {
    PLAYER1("Player P1"),
    PLAYER2("Player P2");

    private final String id;

    Player(String id) {
        this.id = id;
    }

    public String id(){
        return id;
    }
}
