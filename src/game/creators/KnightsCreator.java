package game.creators;

import game.Board;
import game.BoardGame;
import game.KnightsGame;
import game.enums.Field;

import static game.Board.TILE_COUNT;

public class KnightsCreator implements GameCreator {
    @Override
    public BoardGame crateGame() {
        return new KnightsGame(setUpBoard());
    }

    private Board setUpBoard() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = game.enums.Field.EMPTY;
                if (i < 2) {
                    fields[i][j] = game.enums.Field.BLACK_MAN;
                } else if (i > 5) {
                    fields[i][j] = game.enums.Field.WHITE_MAN;
                }
            }
        }
        return new Board(fields);
    }
}
