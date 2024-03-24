package AI;

import Strategy.StrategyFactory;
import boards.TicTacToeBoard;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {

    StrategyFactory strategyFactory = new StrategyFactory();
    public Move suggestMove( Player player, Board board ) {
        if( board instanceof TicTacToeBoard)
        {
            TicTacToeBoard b = ( TicTacToeBoard ) board;
            Cell suggestion =  strategyFactory.getStrategy(b, player).getOptimalMove( b, player );
            if( suggestion != null){
                return new Move( suggestion, player );
            }
            throw new IllegalStateException();

        }
        throw new IllegalStateException();
    }

}
