package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.RuleEngine;
import utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement{
    private static DefensivePlacement defensivePlacement;

    //singleton class, no constructor available
    private DefensivePlacement()
    {

    }
    public static synchronized DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement)  Utils.getIfNull( defensivePlacement, DefensivePlacement::new );
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
                    Move move = new Move( Cell.getCell(i, j), player.flip());
                    //The move is creating a copy and returning a new board
                    TicTacToeBoard boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return Cell.getCell(i, j);
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
