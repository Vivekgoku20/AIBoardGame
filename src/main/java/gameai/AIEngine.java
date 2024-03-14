package gameai;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

public class AIEngine {


    public Move suggestMove( Player computer, Board board ) {
        if( board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1 = ( TicTacToeBoard ) board;
            Move suggestion;
            if( LessThanMoves( board1, 3 )) {
                suggestion = getBasicMove(computer, board1 );
            } else{
                suggestion = getSmartMove( compute, board );
            }

            if( suggestion != null )
                return suggestion;
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    private Move getSmartMove( Player computer, TicTacToeBoard board )
    {
        //If possible to make the winning move, choose that, else make the move that blocks the user from winning
        return null;
    }
    private Move getBasicMove( Player computer, TicTacToeBoard board )
    {
        for( int i = 0 ; i<3 ; i++ )
        {
            for( int j=0 ; j<3; j++ )
            {
                if( board.getCell( i, j ) == null )
                    return new Move( new Cell( i, j ), computer);
            }
        }
        return null;
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
