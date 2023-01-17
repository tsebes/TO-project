package game;

import game.creators.KnightsCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class KnightsGameTest {

    BoardGame game;

    @BeforeEach
    public void createGame() {
        game = new KnightsCreator().createGame();
    }

    @ParameterizedTest
    @CsvSource({"7, 0", "7, 1", "7, 2", "7, 3", "7, 4", "7, 5", "7, 6", "7, 7"})
    public void shouldFindPossibleMovesWhitePiecesSecondRow(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(5, y));

        game.updatePossibleMoves(pieceCoordinates);

        assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "0, 1", "0, 2", "0, 3", "0, 4", "0, 5", "0, 6", "0, 7"})
    public void shouldFindPossibleMovesBlackPiecesSecondRow(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(2, y));

        game.updatePossibleMoves(pieceCoordinates);

        assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @ParameterizedTest
    @CsvSource({"6, 0", "6, 1", "6, 2", "6, 3", "6, 4", "6, 5", "6, 6", "6, 7"})
    public void shouldFindPossibleMovesWhitePiecesFirstRow(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(5, y));

        game.updatePossibleMoves(pieceCoordinates);

        assertEquals(possibleMoves, game.getPossibleMoves());
    }

    @ParameterizedTest
    @CsvSource({"1, 0", "1, 1", "1, 2", "1, 3", "1, 4", "1, 5", "1, 6", "1, 7"})
    public void shouldFindPossibleMovesBlackPiecesFirstRow(int x, int y) {
        Coordinates pieceCoordinates = new Coordinates(x, y);
        Set<Coordinates> possibleMoves = new HashSet<>();
        possibleMoves.add(new Coordinates(2, y));

        game.updatePossibleMoves(pieceCoordinates);

        assertEquals(possibleMoves, game.getPossibleMoves());
    }


    @Test
    public void shouldMakeUndo() {
        Coordinates start = new Coordinates(7, 0);
        Coordinates end = new Coordinates(5, 0);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();

        Assertions.assertTrue(game.getBoard().getField(end).isEmpty());
        Assertions.assertSame(game.getBoard().getField(start).getPiece(), movedPiece);
    }

    @Test
    public void shouldMakeRedo() {
        Coordinates start = new Coordinates(7, 0);
        Coordinates end = new Coordinates(5, 0);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();
        game.redo();

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldNotMakeRedo() {
        Coordinates start = new Coordinates(7, 0);
        Coordinates end = new Coordinates(5, 0);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.redo();

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }
}