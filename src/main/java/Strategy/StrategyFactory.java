package Strategy;

import boards.TicTacToeBoard;
import game.Player;

public class StrategyFactory {
    public Strategy getStrategy(TicTacToeBoard b, Player player) {
        Strategy strategy = null;
        int threshold = 2;
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
