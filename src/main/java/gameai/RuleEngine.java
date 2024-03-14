    package gameai;

import boards.TicTacToeBoard;
import game.Board;
import game.GameResult;

public class RuleEngine {
    public GameResult getState(Board board )
    {
        if ( board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            boolean row_complete = true;
            boolean col_complete = true;
            boolean diag_complete = true;
            boolean rev_diag_complete = true;
            String firstCharacter = ".";
            for(int i = 0 ;i<3;i++)
            {
                firstCharacter = board1.getCell( i, 0 );
                row_complete = firstCharacter != null;
                if (firstCharacter!=null) {
                    for(int j=1;j<3;j++)
                    {
                        if(!(firstCharacter.equals(board1.getCell( i, j ))))
                        {
                            row_complete = false;
                            break;
                        }
                    }
                }
                if(row_complete)
                    break;
            }

            if( row_complete )
            {
                return new GameResult( true, firstCharacter );
            }

            for(int i = 0 ;i<3;i++)
            {
                firstCharacter = board1.getCell( 0, i );
                col_complete = firstCharacter!=null;
                if (firstCharacter!=null) {
                    for(int j=1;j<3;j++)
                    {
                        if(!firstCharacter.equals(board1.getCell( j, i )))
                        {
                            col_complete = false;
                            break;
                        }
                    }
                }
                if(col_complete)
                    break;
            }

            if( col_complete )
            {
                return new GameResult(true, firstCharacter );
            }

            firstCharacter = board1.getCell( 0, 0 );
            diag_complete = firstCharacter!=null;

            for(int i = 1 ;i<3;i++)
            {
                if (firstCharacter!=null) {
                    if (!firstCharacter.equals(board1.getCell(i, i))) {
                        diag_complete = false;
                        break;
                    }
                }
            }

            if( diag_complete )
            {
                return new GameResult(true, firstCharacter );
            }

            firstCharacter = board1.getCell( 0, 2 );
            rev_diag_complete = firstCharacter!=null;

            for(int i = 0 ;i<2;i++)
            {
                if (firstCharacter!=null) {
                    if (!firstCharacter.equals(board1.getCell(2 - i, i))) {
                        rev_diag_complete = false;
                        break;
                    }
                }
            }

            if( rev_diag_complete )
            {
                return new GameResult(true, firstCharacter );
            }

            int countOfFilledCells = 0;
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
            return new GameResult( isOver, "-" );
        }
        else {
            return new GameResult(false, "******invalid board******");
        }
    }
}
