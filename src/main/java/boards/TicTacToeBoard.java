package boards;

import gamerules.Rule;

import gamerules.RuleSet;

import game.Cell;
import game.GameState;
import game.Move;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {
    String[][] cells = new String[3][3];
    History history = new History();
    public String getCell(int i, int j)
    {
        return cells[i][j];
    }
    public static RuleSet getRules()
    {
        RuleSet rules = new RuleSet();

        //using method inference since the order of arguments is in line
        rules.add(  new Rule( board -> outerTraversal(board::getSymbol) ) );
        //cannot use method inference since the arguments are interchanged in the lambda
        rules.add( new Rule(board -> outerTraversal((i, j) -> board.getSymbol(j, i) ) ) );
        rules.add( new Rule( board -> innerTraversal( i -> board.getSymbol( i, i ) ) ) );
        rules.add( new Rule( board -> innerTraversal( i -> board.getSymbol( i, 2- i ) ) ) );
        rules.add( new Rule( board -> {
            boolean isOver = true;
            for(int i = 0 ;i<3;i++){
                for(int j = 0;j<3;j++)
                {
                    if(board.getSymbol( i, j ) == null ){
                        isOver = false;
                        break; }
                }
                if( !isOver )
                    break;
            }
            return new GameState( isOver, "-" );
        } ) );

        return rules;
    }

    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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

    private static GameState innerTraversal(Function<Integer, String> traverse) {

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

    public void setCell( Cell cell, String playerSymbol ) throws IllegalArgumentException {
        if( cells[cell.getRow()][cell.getCol()] == null ){
            cells[ cell.getRow() ][ cell.getCol() ] = playerSymbol;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String toString()
    {
        String result="";
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j]==null?"_":cells[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    @Override
    public TicTacToeBoard move(Move move) {
        history.add(new Representation(this));
        TicTacToeBoard board = copy();
        board.setCell(move.getCell(), move.getPlayer().getPlayerSymbol());
        return board;
    }

    public String getSymbol(int i, int j) {
        return cells[i][j];
    }
    public TicTacToeBoard copy()
    {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy( cells[i], 0, ticTacToeBoard.cells[i], 0, 3 );
        }
        ticTacToeBoard.history = history;
        return ticTacToeBoard;
    }
    public enum Symbol{
        X("X"), O("O");
        String marker;
        Symbol(String marker){
            this.marker = marker;
        }

        public String marker() {
            return this.marker;
        }
    }
}

