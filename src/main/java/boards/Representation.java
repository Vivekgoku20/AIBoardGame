package boards;

public class Representation {
    String representation;

    public Representation(TicTacToeBoard board) {
        representation = board.toString();
    }
}
