package game;


import game.enums.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckersGame extends BoardGame {
    private int whitePieces = 12;
    private int blackPieces = 12;

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        if(isJumpMovePossible(currentTurn)){
            possibleMoves.addAll(jumpMoves(piece));
            notifyBoardObservers();
            setPieces(currentTurn);
            checkWin();
        } else if (isMovePossible(piece)) {
            possibleMoves.addAll(basicMoves(piece));
            notifyBoardObservers();
        } else {
            setWhenNoMoves(currentTurn);
            System.out.println("No moves possible");
            checkWin();
        }
    }

    private Set<Coordinates> basicMoves(Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if (board.getField(piece).getPlayer() == Player.WHITE) {
                    if (piece.x() > 0 && piece.y() < board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() + 1));
                    }
                    if (piece.x() > 0 && piece.y() > 0 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() - 1));
                    }
                } else {
                    if (piece.x() < board.TILE_COUNT - 1 && piece.y() < board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() + 1));
                    }
                    if (piece.x() < board.TILE_COUNT - 1 && piece.y() > 0 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() - 1));
                    }
                }
            }
            case KING -> {

                for (int i = 1; i < board.TILE_COUNT; i++) {
                    if (piece.x() - i >= 0 && piece.y() + i < board.TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - i, piece.y() + i));
                    }
                    if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - i, piece.y() - i));
                    }
                    if (piece.x() + i < board.TILE_COUNT && piece.y() + i < board.TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + i, piece.y() + i));
                    }
                    if (piece.x() + i < board.TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + i, piece.y() - i));
                    }
                }
            }
        }
        return possibleBasic;
    }

    private Set<Coordinates> jumpMoves(Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if (board.getField(piece).getPlayer() == Player.WHITE) {
                    if (piece.x() > 1 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                    }
                    if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                    }
                    if (board.isMultipleTake()) {
                        if (piece.x() < board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                        }
                        if (piece.x() < board.TILE_COUNT - 2 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                        }
                    }
                } else {
                    if (piece.x() < board.TILE_COUNT - 2 && piece.y() < board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                    }
                    if (piece.x() < board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                    }
                    if (board.isMultipleTake()) {
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
                List<Coordinates> opponents = new ArrayList<>();
                for (int i = 1; i < board.TILE_COUNT; i++) {
                    if (piece.x() - i >= 0 && piece.y() + i < board.TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).getPlayer() != currentTurn && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty() == false) {
                        opponents.add(new Coordinates(piece.x() - i, piece.y() + i));
                    }

                    for (Coordinates opponent : opponents) {
                        for(int j = 1;opponent.x() - j >= 0 && opponent.y() + j < board.TILE_COUNT&& opponent.y() + j >= 0 && opponent.x() - j <board.TILE_COUNT;j++){
                            if (opponent.x() - j >= 0 && opponent.y() + j < board.TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() + j)).isEmpty()) {
                                possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() + j));
                            }
                            else
                                break;
                        }
                    }
                }

                for(int i = 1; i < board.TILE_COUNT; i++) {
                    opponents.clear();
                    if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).getPlayer() != currentTurn && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty() == false) {
                        opponents.add(new Coordinates(piece.x() - i, piece.y() - i));
                    }
                    for (Coordinates opponent : opponents) {
                        for(int j = 1;opponent.x() - j >= 0 && opponent.y() - j < board.TILE_COUNT&& opponent.y() - j >= 0 && opponent.x() - j <board.TILE_COUNT;j++){
                            if (opponent.x() - j >= 0 && opponent.y() - j < board.TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() - j)).isEmpty()) {
                                possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() - j));
                            }
                            else
                                break;
                        }
                    }
                }

                for(int i = 1; i < board.TILE_COUNT; i++) {
                    opponents.clear();

                    if (piece.x() + i < board.TILE_COUNT && piece.y() + i < board.TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).getPlayer() != currentTurn && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty() == false) {
                        opponents.add(new Coordinates(piece.x() + i, piece.y() + i));
                    }
                    for (Coordinates opponent : opponents) {
                        for(int j=1;opponent.x() + j >= 0 && opponent.y() + j < board.TILE_COUNT;j++){
                            if (opponent.x() + j >= 0 && opponent.y() + j < board.TILE_COUNT&& opponent.y() + j >= 0 && opponent.x() + j <board.TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() + j)).isEmpty()) {
                                possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() + j));
                            }
                            else
                                break;
                        }
                    }
                }
                for(int i = 1; i < board.TILE_COUNT; i++) {
                    opponents.clear();
                    if (piece.x() + i < board.TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).getPlayer() != currentTurn && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty() == false) {
                        opponents.add(new Coordinates(piece.x() + i, piece.y() - i));
                    }
                    for (Coordinates opponent : opponents) {
                        for(int j = 1;opponent.x() + j >= 0 && opponent.y() - j < board.TILE_COUNT && opponent.y() - j >= 0 && opponent.x() + j <board.TILE_COUNT ;j++){
                            if (opponent.x() + j >= 0 && opponent.y() - j < board.TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() - j)).isEmpty()) {
                                possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() - j));
                            }
                            else
                                break;
                        }
                    }
                }
            }
        }

        return possibleJump;
    }
   public boolean isJumpMovePossible(Player player) {
        for (int i = 0; i < board.TILE_COUNT; i++) {
            for (int j = 0; j < board.TILE_COUNT; j++) {
                if (board.getField(new Coordinates(i, j)).getPlayer() == player) {
                    if (isJumpPossible(new Coordinates(i, j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isJumpPossible(Coordinates piece) {
        if(jumpMoves(piece).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean isMovePossible(Coordinates piece) {
        if(basicMoves(piece).isEmpty())
            return false;
        else
            return true;
    }

    private int getWhitePieces() {
        return whitePieces;
    }
    private int getBlackPieces() {
        return blackPieces;
    }


    private void setPieces(Player player){
        if(player == Player.WHITE){
            whitePieces--;
        }else{
            blackPieces--;
        }
    }
    private void setWhenNoMoves(Player player){
        if(player == Player.WHITE){
            whitePieces = 0;
        }else{
            blackPieces = 0;
        }
    }

    private void checkWin(){
        if(getWhitePieces() == 0){
            System.out.println("Black win");
            //zmień panel na menu

        }
        if(getBlackPieces() == 0){
            System.out.println("White win");
            //zmień panel na menu
        }
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
