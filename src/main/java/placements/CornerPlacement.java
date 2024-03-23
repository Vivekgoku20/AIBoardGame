package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;
    private CornerPlacement(){

    }
    public static synchronized CornerPlacement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull( cornerPlacement, CornerPlacement::new );
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player ) {
        return Optional.ofNullable( placeInCorner( board ) );
    }

    public Cell placeInCorner( TicTacToeBoard board )
    {
        int[][] corners = new int[][]{ {0,0}, {0,2}, {2,0}, {2,2} };
        for (int i = 0; i < 4; i++) {
            int row = corners[i][0];
            int column = corners[i][1];
            if (board.getSymbol(row, column) == null )
                return Cell.getCell( row, column );
        }
        return null;
    }
    @Override
    public Placement next() {
        return null;
    }
}
