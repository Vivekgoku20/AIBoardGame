package game;

public class Player {
    private int timeUsedInMillis;
    private String playerSymbol;
    private User Id;
    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public Player flip() {
        String flipPlayerSymbol = playerSymbol.equals( "X" )? "O":"X";
        return new Player( flipPlayerSymbol );
    }

    public void setTimeTaken( int timeUsedInMillis ){
        this.timeUsedInMillis += timeUsedInMillis;
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }
}
