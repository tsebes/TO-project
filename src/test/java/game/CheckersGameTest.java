package game;

import game.creators.CheckersCreator;
import game.enums.PieceType;
import game.enums.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static game.Board.TILE_COUNT;
import static org.mockito.Mockito.*;

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

    @Test
    public void shouldMoveWhitePiece() {
        Coordinates start = new Coordinates(5, 4);
        Coordinates end = new Coordinates(4, 3);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        Assertions.assertTrue(game.getBoard().getField(end).isEmpty());

        game.move(start, end);

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldMoveBlackPiece() {
        Coordinates start = new Coordinates(2, 5);
        Coordinates end = new Coordinates(3, 4);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        Assertions.assertTrue(game.getBoard().getField(end).isEmpty());

        game.move(start, end);

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldMakeUndoMove_whenWhitePiece() {
        Coordinates start = new Coordinates(5, 4);
        Coordinates end = new Coordinates(4, 3);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();

        Assertions.assertTrue(game.getBoard().getField(end).isEmpty());
        Assertions.assertSame(game.getBoard().getField(start).getPiece(), movedPiece);
    }

    @Test
    public void shouldMakeUndoMove_whenBlackPiece() {
        Coordinates start = new Coordinates(2, 5);
        Coordinates end = new Coordinates(3, 4);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();

        Assertions.assertTrue(game.getBoard().getField(end).isEmpty());
        Assertions.assertSame(game.getBoard().getField(start).getPiece(), movedPiece);
    }

    @Test
    public void shouldMakeRedoMove_whenWhitePiece() {
        Coordinates start = new Coordinates(5, 4);
        Coordinates end = new Coordinates(4, 3);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();
        game.redo();

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldMakeRedoMove_whenBlackPiece() {
        Coordinates start = new Coordinates(2, 5);
        Coordinates end = new Coordinates(3, 4);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.undo();
        game.redo();

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldNotMakeRedoMove_whenNoUndoMove() {
        Coordinates start = new Coordinates(2, 5);
        Coordinates end = new Coordinates(3, 4);
        Piece movedPiece = game.getBoard().getField(start).getPiece();

        game.move(start, end);
        game.redo();

        Assertions.assertTrue(game.getBoard().getField(start).isEmpty());
        Assertions.assertSame(game.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldTakeWhitePiece() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = new Field();
            }
        }
        fields[0][1].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[3][4].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[7][0].setPiece(new Piece(Player.WHITE, PieceType.MAN));
        fields[4][3].setPiece(new Piece(Player.WHITE, PieceType.MAN));

        Board customBoard = new Board(fields);

        CheckersCreator mockCreator = mock(CheckersCreator.class);
        when(mockCreator.createGame()).thenReturn(new CheckersGame(customBoard));
        BoardGame startedGame = mockCreator.createGame();

        Coordinates start = new Coordinates(4, 3);
        Coordinates taken = new Coordinates(3,4);
        Coordinates end = new Coordinates(2, 5);

        Piece movedPiece = startedGame.getBoard().getField(start).getPiece();

        Assertions.assertTrue(startedGame.getBoard().getField(end).isEmpty());

        startedGame.move(start, end);

        Assertions.assertTrue(startedGame.getBoard().getField(start).isEmpty());
        Assertions.assertTrue(startedGame.getBoard().getField(taken).isEmpty());
        Assertions.assertSame(startedGame.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldTakeBlackPiece() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = new Field();
            }
        }
        fields[0][1].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[2][3].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[7][0].setPiece(new Piece(Player.WHITE, PieceType.MAN));
        fields[3][4].setPiece(new Piece(Player.WHITE, PieceType.MAN));

        Board customBoard = new Board(fields);

        CheckersCreator mockCreator = mock(CheckersCreator.class);
        when(mockCreator.createGame()).thenReturn(new CheckersGame(customBoard));
        BoardGame startedGame = mockCreator.createGame();

        Coordinates start = new Coordinates(2, 3);
        Coordinates taken = new Coordinates(3,4);
        Coordinates end = new Coordinates(4, 5);

        startedGame.currentTurn = Player.BLACK;

        Piece movedPiece = startedGame.getBoard().getField(start).getPiece();

        Assertions.assertTrue(startedGame.getBoard().getField(end).isEmpty());

        startedGame.move(start, end);

        Assertions.assertTrue(startedGame.getBoard().getField(start).isEmpty());
        Assertions.assertTrue(startedGame.getBoard().getField(taken).isEmpty());
        Assertions.assertSame(startedGame.getBoard().getField(end).getPiece(), movedPiece);
    }

    @Test
    public void shouldWhiteChangeToKing() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = new Field();
            }
        }
        fields[0][1].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[7][0].setPiece(new Piece(Player.WHITE, PieceType.MAN));
        fields[1][6].setPiece(new Piece(Player.WHITE, PieceType.MAN));

        Board customBoard = new Board(fields);

        CheckersCreator mockCreator = mock(CheckersCreator.class);
        when(mockCreator.createGame()).thenReturn(new CheckersGame(customBoard));
        BoardGame startedGame = mockCreator.createGame();

        Coordinates start = new Coordinates(1, 6);
        Coordinates end = new Coordinates(0, 7);

        startedGame.move(start, end);

        Assertions.assertEquals(startedGame.getBoard().getField(end).getPiece().getPieceType(), PieceType.KING);
    }

    @Test
    public void shouldBlackChangeToKing() {
        Field[][] fields = new Field[TILE_COUNT][TILE_COUNT];
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                fields[i][j] = new Field();
            }
        }
        fields[0][1].setPiece(new Piece(Player.BLACK, PieceType.MAN));
        fields[7][0].setPiece(new Piece(Player.WHITE, PieceType.MAN));
        fields[6][7].setPiece(new Piece(Player.BLACK, PieceType.MAN));

        Board customBoard = new Board(fields);

        CheckersCreator mockCreator = mock(CheckersCreator.class);
        when(mockCreator.createGame()).thenReturn(new CheckersGame(customBoard));
        BoardGame startedGame = mockCreator.createGame();

        startedGame.currentTurn = Player.BLACK;

        Coordinates start = new Coordinates(6, 7);
        Coordinates end = new Coordinates(7, 6);

        startedGame.move(start, end);

        Assertions.assertEquals(startedGame.getBoard().getField(end).getPiece().getPieceType(), PieceType.KING);
    }

}
