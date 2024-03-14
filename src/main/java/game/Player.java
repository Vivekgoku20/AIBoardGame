package game;

public class Player {
    private String playerSymbol;

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
}
