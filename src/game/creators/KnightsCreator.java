package game.creators;

import game.Board;
import game.BoardGame;
import game.Field;
import game.KnightsGame;
import game.enums.PieceEnum;
import game.enums.Player;

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
                fields[i][j] = new Field();
                if (i < 2) {
                    fields[i][j] = new Field(Player.BLACK, PieceEnum.MAN);
                } else if (i > 5) {
                    fields[i][j] = new Field(Player.WHITE, PieceEnum.MAN);
                }
            }
        }
        return new Board(fields);
    }
}
