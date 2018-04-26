package kalah;

public class Board {

    private Pit[] _pits;

    public Board(){
        _pits = new Pit[14];
        initialise();
    }
    private void initialise() {
        Player player = Player.Player1;
        for(int i = 0; i < 6; i++) {
            _pits[i] = new House(player, i + 1);
        }
        _pits[6] = new Store(player);

        player = Player.Player2;
        for(int i = 7; i < 13; i++) {
            _pits[i] = new House(player, i-1);
        }
        _pits[13] = new Store(player);

    }

    public boolean isInPlay() {
        for(int i = 0; i < 6; i++){
            if(!_pits[i].isEmpty()){
                break;
            }
            if(i == 5){
                return false;
            }
        }
        for(int i = 7; i < 13; i++){
            if(!_pits[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public int[] getBoardState(){
        int[] simplePits = new int[14];
        for(int i = 0; i < 14; i++){
            simplePits[i] = _pits[i]._seedCount;
        }
        return simplePits;
    }

    public void distribute(Player currentPlayer, int pitNo) {
       int index;
        if(currentPlayer.equals(Player.Player1)){
            index = pitNo-1;
        }else{
            index = pitNo + 6;
        }
        int seedCount = _pits[index]._seedCount;
        _pits[index]._seedCount = 0;
        for(int i = 1; i <= seedCount; i++){
            if(!_pits[(index+i)%14].increment(currentPlayer)){
                seedCount++;
            }

            if(i == seedCount){
                if(_pits[i].isEmpty() && _pits[i].getClass().equals(House.class)){
                    capture(_pits[i]);
                }
                /**
                 * and end on mancala store
                 * promptRepeatTurn();
                 * or house belongs to player and is empty
                 * capture();
                 */
            }
        }

    }

    private void capture(Pit pit) {

    }
}
