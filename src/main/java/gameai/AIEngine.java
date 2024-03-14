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
                suggestion = getSmartMove( computer, board1 );
            }
            if( suggestion != null )
                return suggestion;
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    private Move getSmartMove( Player player, TicTacToeBoard board )
    {
        //If possible to either draw or win the game, we will choose it
        RuleEngine ruleEngine = new RuleEngine();
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(board.getSymbol( i, j ) == null )
                {
                    Move move = new Move( new Cell( i, j ), player ) ;
                    board.move( move );
                    if( ruleEngine.getState(board).isOver() ){
                        return move;
                    }
                }
            }
        }

        //defensive moves, i.e, preventing the opponent from winning, by making his winning move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getSymbol(i,j) == null){
                    Move move = new Move( new Cell(i, j), player.flip());
                    board.move(move);
                    if(ruleEngine.getState(board).isOver()){
                        return new Move( new Cell(i,j), player );
                    }
                }
            }
        }
        //If we don't have a winning or a defensive move, we will choose a basic move
        return getBasicMove( player, board );
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
