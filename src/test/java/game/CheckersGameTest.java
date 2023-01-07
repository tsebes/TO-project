package game;

import game.creators.CheckersCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class CheckersGameTest {

    BoardGame game;

    @BeforeEach
    public void createGame() {
        game = new CheckersCreator().createGame();
    }

    @ParameterizedTest
    @CsvSource({"5, 2", "5, 4", "5, 6"})
    public void shouldFindPossibleMovesWhitePiece(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(x - 1, y - 1));
        possibleMoves.add(new Coordinates(x - 1, y + 1));

        game.updatePossibleMoves(pieceCoordinates);

        Assertions.assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @ParameterizedTest
    @CsvSource({"2, 1", "2, 3", "2, 5"})
    public void shouldFindPossibleMovesBlackPiece(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(x + 1, y - 1));
        possibleMoves.add(new Coordinates(x + 1, y + 1));

        game.updatePossibleMoves(pieceCoordinates);

        Assertions.assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @Test
    public void shouldFindOnePossibleMove_whenWhitePieceIsOnEdge() {
        Coordinates pieceCoordinates = new Coordinates(5, 0);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(4, 1));

        game.updatePossibleMoves(pieceCoordinates);

        Assertions.assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @Test
    public void shouldFindOnePossibleMove_whenBlackPieceIsOnEdge() {
        Coordinates pieceCoordinates = new Coordinates(2, 7);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(3, 6));

        game.updatePossibleMoves(pieceCoordinates);

        Assertions.assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @ParameterizedTest
    @CsvSource({"6, 1", "6, 3", "6, 5", "6, 7", "7, 0", "7, 2", "7, 4", "7, 6"})
    public void shouldNotFindMoves_whenPieceCannotMove(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();

        game.updatePossibleMoves(pieceCoordinates);

        Assertions.assertEquals(possibleMoves, game.getPossibleMoves());
    }

}
