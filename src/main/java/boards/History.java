package boards;

import java.util.ArrayList;
import java.util.List;

class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        for (int i = 0; i <= boards.size() - (moveIndex + 1); i++) {
            boards.remove(boards.size() - 1);
        }
        return boards.get(moveIndex);
    }

    public Representation undo() {
        if (boards.isEmpty())
            throw new IllegalArgumentException();
        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public void add(Representation representation) {
        boards.add(representation);
    }

}
