package game.creators;

import game.Board;
import game.BoardGame;
import game.CheckersGame;
import game.enums.Field;

import static game.Board.TILE_COUNT;

public class CheckersCreator implements GameCreator {
    @Override
    public BoardGame crateGame() {
        return new CheckersGame(setUpBoard());
    }

    private Board setUpBoard() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = game.enums.Field.EMPTY;
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        fields[i][j] = Field.BLACK_MAN;
                    } else if (i > 4) {
                        fields[i][j] = Field.WHITE_MAN;
                    }
                }
            }
        }
        return new Board(fields);
    }
}
