    package gameai;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

    public class RuleEngine {
    public GameState getState(Board board )
    {
        if ( board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            GameState rowWin = outerTraversal( ( i, j )-> board1.getSymbol( i, j ) );
            if( rowWin.isOver() )   return  rowWin;

            GameState colWin = outerTraversal( ( i, j )-> board1.getSymbol( j, i ) );
            if( colWin.isOver() )   return  colWin;

            GameState diagnolWin = innerTraversal( i -> board1.getSymbol( i, i ) );
            if ( diagnolWin.isOver() )   return  diagnolWin;

            GameState revDiagonalWin = innerTraversal( i -> board1.getSymbol( i, 2-i ) );
            if( revDiagonalWin.isOver() ) return revDiagonalWin;

            boolean isOver = true;
            for(int i = 0 ;i<3;i++)
            {
                for(int j = 0;j<3;j++)
                {
                    if(board1.getCell( i, j ) == null )
                    {
                        isOver = false;
                        break;
                    }
                }
                if( !isOver )
                    break;
            }
            return new GameState( isOver, "-" );
        }
        else {
            return new GameState(false, "******invalid board******");
        }
    }

        private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
            GameState resultGameState = new GameState( false, "-");
            for(int i = 0 ;i<3;i++)
                {
                    int finalI = i;
                    GameState TraversalGameState = innerTraversal( j -> next.apply(finalI, j ) );//Inner traversal lambda
                    if( TraversalGameState.isOver() ) {
                        resultGameState = TraversalGameState;
                        break;
                    }
                }
            return resultGameState;
        }

        private GameState innerTraversal( Function< Integer, String> traverse ) {

            GameState resultGameState = new GameState( false, "-");
            boolean possibleStreak = true;
            for(int j = 0 ; j<3 ;j++)
            {
                if( ( traverse.apply( j ) == null ) || !( traverse.apply( 0 ).equals( traverse.apply( j ) ) ) ){
                        possibleStreak = false;
                        break;
                    }
            }
            if( possibleStreak )
                return new GameState( true, traverse.apply( 0 )  );
            return  resultGameState;
        }
    }
