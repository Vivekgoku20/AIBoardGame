package gamerules;

import boards.TicTacToeBoard;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {


    public Move suggestMove( Player player, Board board ) {
        if( board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1 = ( TicTacToeBoard ) board;
            Cell suggestion;
            int threshold = 3;
            if( LessThanMoves( board1, threshold )) {
                suggestion = getBasicMove( board1 );
            } else if ( LessThanMoves( board1, threshold + 1)){
                suggestion = getCellToPlay( player, board1 );
            }
            else
            {
                suggestion = getOptimalCellToPlay( player, board1 );
            }
            if( suggestion != null )
                return new Move( suggestion, player );
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    private Cell getOptimalCellToPlay( Player player, TicTacToeBoard board )
    {
        //1. if you have a winning move, play it
        //2. if opponent has a winning move, block it
        //3. if you have a fork, play it
        //4. if opponent has a fork, block it
        //5. if center is available, take it
        //6. if corner is available, take it
        //All the above logic is taken care of by placement classes, by executing them in order, using chained responsibility and singleton design pattern
        Placement placement = OffensivePlacement.get();
        while ( placement.next() != null ){
            Optional<Cell> place = placement.place( board, player );
            if( placement.place(board, player ).isPresent() ){
                return place.get();
            }
            placement = placement.next();
        }
        return null;
    }
    private Cell getCellToPlay( Player player, TicTacToeBoard board )
    {
        //If possible to either draw or win the game, we will choose it
        RuleEngine ruleEngine = new RuleEngine();
        Cell best = offense(player, board, ruleEngine);
        if ( best != null) return best;

        //defensive moves, i.e, preventing the opponent from winning, by making his winning move
        best = defense(player, board, ruleEngine);
        if ( best != null) return best;
        //If we don't have a winning or a defensive move, we will choose a basic move
        return getBasicMove( board );
    }

    private static Cell defense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getSymbol(i,j) == null){
                    Move move = new Move( new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private static Cell offense(Player player, TicTacToeBoard board, RuleEngine ruleEngine) {
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(board.getSymbol( i, j ) == null )
                {
                    Move move = new Move( new Cell( i, j ), player) ;
                    TicTacToeBoard boardCopy = board.move( move );
                    if( ruleEngine.getState(boardCopy).isOver() ){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    private Cell getBasicMove( TicTacToeBoard board )
    {
        for( int i = 0 ; i<3 ; i++ )
        {
            for( int j=0 ; j<3; j++ )
            {
                if( board.getCell( i, j ) == null )
                    return new Cell( i, j );
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
