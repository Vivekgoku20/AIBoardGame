package placements;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.RuleEngine;
import utils.Utils;

import java.util.Optional;

public class OffensivePlacement implements Placement {
    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement(){

    }
    @Override
    public Optional<Cell> place( TicTacToeBoard board, Player player ) {
        return Optional.ofNullable(offense( player, board, ruleEngine ) );
    }

    private Cell offense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(board.getSymbol( i, j ) == null )
                {
                    Move move = new Move( new Cell( i, j ), player) ;
                    TicTacToeBoard boardCopy = (TicTacToeBoard) board.copy();
                    boardCopy.move( move );
                    if( ruleEngine.getState(boardCopy).isOver() ){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
    public static synchronized Placement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull( offensivePlacement, OffensivePlacement::new );
        return offensivePlacement;
    }
    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }
}
