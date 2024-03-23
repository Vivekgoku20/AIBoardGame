package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import gamerules.GameInfo;
import utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement forkPlacement;

    private ForkPlacement(){

    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player ) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getGameInfo( board );
        if( gameInfo.hasAFork()){
            best = gameInfo.forkCell();
        }
        return Optional.ofNullable( best );
    }

    public static synchronized ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull( forkPlacement, ForkPlacement::new );
        return forkPlacement;
    }
    @Override
    public Placement next() {
        return CentrePlacement.get();
    }
}
