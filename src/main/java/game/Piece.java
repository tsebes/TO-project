package game;

import game.enums.PieceType;
import game.enums.Player;

import java.util.Set;

public class Piece {

    private final Player player;
    private PieceType pieceType;
    private Strategy currentStrategy;

    public Piece(Player player, PieceType pieceType) {
        this.player = player;
        this.pieceType = pieceType;
        switch (pieceType) {
            case MAN -> currentStrategy = new ManStrategy();
            case KING -> currentStrategy = new KingStrategy();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Set<Coordinates> getPossibleMoves(Board board, Coordinates piece) {
        return currentStrategy.getPossibleMoves(board, piece);
    }

    public Set<Coordinates> getPossibleJumps(Board board, Coordinates piece) {
        return currentStrategy.getPossibleJumps(board, piece);
    }
}
