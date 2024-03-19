package placements;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.RuleEngine;
import utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement{
    private static Placement defensivePlacement;

    //singleton class, no constructor available
    private DefensivePlacement()
    {

    }
    public static synchronized Placement get() {
        defensivePlacement = (DefensivePlacement)Utils.getIfNull( defensivePlacement, DefensivePlacement::new );
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player ) {
        return Optional.ofNullable(defense( player, board, ruleEngine ));
    }

    private Cell defense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getSymbol(i,j) == null){
                    Move move = new Move( new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
    @Override
    public Placement next() {
        return ForkPlacement.get();
    }
}