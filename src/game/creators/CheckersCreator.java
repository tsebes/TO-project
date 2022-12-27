package game.creators;

import game.Board;
import game.BoardGame;
import game.CheckersGame;
import game.Field;
import game.enums.PieceEnum;
import game.enums.Player;

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
                fields[i][j] = new Field();
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        fields[i][j] = new Field(Player.BLACK, PieceEnum.MAN);
                    } else if (i > 4) {
                        fields[i][j] = new Field(Player.WHITE, PieceEnum.MAN);
                    }
                }
            }
        }
        return new Board(fields);
    }
}
