package boards;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];
    public String getCell(int i, int j)
    {
        return cells[i][j];
    }

    public void setCell( Cell cell, String playerSymbol ) {
        int row = cell.getRow();
        int col = cell.getCol();
        cells[row][col] = playerSymbol;
    }

    public String toString()
    {
        String result="";
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j]==null?"_":cells[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().getPlayerSymbol());
    }
}
