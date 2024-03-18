    package gamerules;

import boards.TicTacToeBoard;
import game.*;

import java.util.HashMap;
import java.util.Map;

    public class RuleEngine {

        Map< String, RuleSet > ruleMap = new HashMap();
        public RuleEngine( )
        {
            ruleMap.put( TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
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