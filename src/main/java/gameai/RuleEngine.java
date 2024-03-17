    package gameai;

import boards.TicTacToeBoard;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

    public class RuleEngine {

        Map< String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap();
        public RuleEngine( )
        {
            ruleMap.put( TicTacToeBoard.class.getName(), new ArrayList< Rule<TicTacToeBoard> >() );
            //using method inference since the order of arguments is in line
            ruleMap.get( TicTacToeBoard.class.getName() ).add( new Rule<TicTacToeBoard>( board -> outerTraversal(board::getSymbol) ) );
            //cannot use method inference since the arguments are interchanged in the lambda
            ruleMap.get( TicTacToeBoard.class.getName() ).add( new Rule<>(board -> outerTraversal((i, j) -> board.getSymbol(j, i))) );
            ruleMap.get( TicTacToeBoard.class.getName() ).add( new Rule<>( board -> innerTraversal( i -> board.getSymbol( i, i ) ) ) );
            ruleMap.get( TicTacToeBoard.class.getName() ).add( new Rule<>( board -> innerTraversal( i -> board.getSymbol( i, 2- i ) ) ) );
            ruleMap.get( TicTacToeBoard.class.getName() ).add( new Rule<>( board -> {
                boolean isOver = true;
                for(int i = 0 ;i<3;i++){
                    for(int j = 0;j<3;j++)
                    {
                        if(board.getCell( i, j ) == null ){
                            isOver = false;
                            break; }
                    }
                    if( !isOver )
                        break;
                }
                return new GameState( isOver, "-" );
            } ) );
        }
        public GameState getState(Board board )
        {
            if ( board instanceof TicTacToeBoard)
            {
                TicTacToeBoard board1 = (TicTacToeBoard) board;
                for( Rule<TicTacToeBoard> r: ruleMap.get(TicTacToeBoard.class.getName()) )
                {
                    GameState gameState = (GameState) r.condition.apply(board1);
                    if( gameState.isOver() ){
                        return gameState;
                    };
                }
               return new GameState( false, "-");
            }
            else {
                return new GameState(false, "******invalid board******");
            }
        }

        private Function< TicTacToeBoard, GameState > outerTraversal = board -> outerTraversal( board::getSymbol);
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

    class Rule< T extends Board >{
        Function< T, GameState > condition;
        public Rule( Function< T, GameState > condition )
        {
            this.condition = condition;
        }
    }
    class GameInfo{
        private boolean isOver;
        private String winner;
        private boolean hasFork;
        private Player player;
        private int numberOfMoves;
        public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfMoves )
        {
            this.isOver = isOver;
            this.winner = winner;
            this.hasFork = hasFork;
            this.player = player;
            this.numberOfMoves = numberOfMoves;
        }
    }
    class GameInfoBuilder{
        private boolean isOver;
        private String winner;
        private boolean hasFork;
        private Player player;
        private int numberOfMoves;
        public GameInfoBuilder isOver( boolean isOver )
        {
            this.isOver = isOver;
            return this;
        }

        public GameInfoBuilder winner( String winner )
        {
            this.winner = winner;
            return this;
        }

        public GameInfoBuilder hasFork( boolean hasFork )
        {
            this.hasFork = hasFork;
            return this;
        }

        public GameInfoBuilder player( Player player )
        {
            this.player = player;
            return this;
        }

        public GameInfoBuilder numberOfMoves( int numberOfMoves )
        {
            this.numberOfMoves = numberOfMoves;
            return this;
        }
        public GameInfo build( )
        {
            return new GameInfo( isOver, winner, hasFork, player, numberOfMoves );
        }
    }