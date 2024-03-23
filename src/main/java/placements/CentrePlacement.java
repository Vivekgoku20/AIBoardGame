package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class CentrePlacement implements Placement{
    private static CentrePlacement centrePlacement;
    private CentrePlacement(){

    }
    public static synchronized CentrePlacement get() {
        centrePlacement = (CentrePlacement) Utils.getIfNull( centrePlacement, CentrePlacement::new );
        return centrePlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell centre = null;
        if(board.getSymbol( 1, 1) == null ) centre = Cell.getCell( 1, 1 );
        return Optional.ofNullable( centre );
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
