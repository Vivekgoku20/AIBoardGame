package gamerules;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;

public class GameEngine {
    public Board start(String type )
    {
        if( type.equals("TicTacToeBoard") )
        {
            return new TicTacToeBoard();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public TicTacToeBoard move(Board board, Move move) throws IllegalArgumentException
    {
        if( board instanceof TicTacToeBoard )
        {
            TicTacToeBoard board1 = ( TicTacToeBoard ) board;
            return(board1.move( move ));
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

}