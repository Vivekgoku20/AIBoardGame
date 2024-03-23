package AI;

import AI.Strategy;
import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;
import gamerules.RuleEngine;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class StrategyFactory {
    public Strategy getStrategy(TicTacToeBoard b, Player player) {
        Strategy strategy = null;
        int threshold = 3;
        if( LessThanMoves( b, threshold )) {
            strategy = new BasicStrategy( );
        } else if ( LessThanMoves( b, threshold + 1)){
            strategy = new SmartStrategy( );
        }
        else if ( player.getTimeUsedInMillis()>10000){
            strategy = new BasicStrategy();
        }
        else
        {
            strategy = new OptimalStrategy( );
        }
        return strategy;
    }

    private boolean LessThanMoves( TicTacToeBoard board, int thershold )
    {
        int count = 0;
        for( int i=0;i<3;i++)
        {
            for(int j = 0; j < 3; j++) {
                if(board.getCell(i,j)!=null)
                    count++;
            }
        }

        return count<thershold;
    }
}
