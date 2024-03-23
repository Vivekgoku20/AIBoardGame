package AI;

import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.RuleEngine;

public class SmartStrategy extends Strategy {
    private final RuleEngine ruleEngine = new RuleEngine();
    private final BasicStrategy basicStrategy = new BasicStrategy();
    @Override
    public Cell getOptimalMove(TicTacToeBoard board, Player player) {
        //If possible to either draw or win the game, we will choose it
        Cell best = offense(player, board, ruleEngine);
        if (best != null) return best;

        //defensive moves, i.e, preventing the opponent from winning, by making his winning move
        best = defense(player, board, ruleEngine);
        if (best != null) return best;
        //If we don't have a winning or a defensive move, we will choose a basic move
        return basicStrategy.getOptimalMove(board, player);
    }
    private Cell defense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getSymbol(i,j) == null){
                    Move move = new Move( Cell.getCell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.   move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return Cell.getCell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell offense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(board.getSymbol( i, j ) == null )
                {
                    Move move = new Move( Cell.getCell( i, j ), player) ;
                    TicTacToeBoard boardCopy = board.move( move );
                    if( ruleEngine.getState(boardCopy).isOver() ){
                        return Cell.getCell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
