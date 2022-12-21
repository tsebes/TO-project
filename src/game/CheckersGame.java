package game;


import game.enums.Player;

import java.util.HashSet;
import java.util.Set;

public class CheckersGame extends BoardGame {

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        // TODO add all possible moves
        if(!board.isMultipleTake()){
            possibleMoves.addAll(basicMoves(piece));
        }
        possibleMoves.addAll(jumpMoves(piece));
        notifyBoardObservers();
    }
    private Set<Coordinates> basicMoves(Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if(board.getField(piece).getPlayer() == Player.WHITE){
                    if (piece.x() > 0 && piece.y() < board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() + 1));
                    }
                    if (piece.x() > 0 && piece.y() > 0 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() - 1));
                    }
                }else{
                    if (piece.x() < board.TILE_COUNT - 1 && piece.y() < board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() + 1));
                    }
                    if (piece.x() < board.TILE_COUNT - 1 && piece.y() > 0 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() - 1));
                    }
                }
            }
            case KING -> {
                //TODO
            }
        }
        return possibleBasic;
    }

    private Set<Coordinates> jumpMoves(Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if(board.getField(piece).getPlayer() == Player.WHITE){
                    if (piece.x() > 1 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                    }
                    if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.BLACK  && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                    }
                    if(board.isMultipleTake()){
                        if (piece.x() < board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                        }
                        if (piece.x() < board.TILE_COUNT - 2 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                        }
                    }
                }else{
                    if (piece.x() < board.TILE_COUNT - 2 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                    }
                    if (piece.x() < board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                    }
                    if(board.isMultipleTake()){
                        if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                        }
                        if (piece.x() > 1 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                        }
                    }
                }

            }
            case KING -> {
                //TODO
            }
        }
        return possibleJump;
    }

    @Override
    public Set<Coordinates> getTaken(Coordinates start, Coordinates end) {
        Set<Coordinates> takenPieces = new HashSet<>();
        Coordinates temp;

        for(int i = 1; i < Math.abs(end.x() - start.x()); i+=1){
            if(start.x() > end.x()){
                if(start.y() > end.y()){
                    temp = new Coordinates(start.x() - 1, start.y() - i);
                }else{
                    temp = new Coordinates(start.x() - 1, start.y() + i);
                }
            }else{
                if(start.y()>end.y()){
                    temp = new Coordinates(start.x() + 1, start.y() - i);
                }else{
                    temp = new Coordinates(start.x() + 1, start.y() + i);
                }
            }
            if(!board.getField(temp).isEmpty()){
                takenPieces.add(temp);
            }
        }
        return takenPieces;
    }

    @Override
    public boolean canJump(Coordinates piece) {
        return ((jumpMoves(piece)).size() > 0);
    }

    @Override
    public boolean jumped(Coordinates start, Coordinates end) {
        return (getTaken(start, end).size() > 0);
    }

}
