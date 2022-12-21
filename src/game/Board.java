package game;

public class Board {

    public static final int TILE_COUNT = 8;
    protected final Field[][] fields;
    private boolean multipleTake;
    private Coordinates current;

    public Board() {
        fields = new Field[TILE_COUNT][TILE_COUNT];
    }

    public Field getField(Coordinates place){
        return fields[place.x()][place.y()];
    }

    public Board(Field[][] fields) {
        this.fields = fields;
    }

    public Field[][] getFields() {
        return fields;
    }

    public boolean isMultipleTake() {
        return multipleTake;
    }

    public void setMultipleTake(boolean multipleTake) {
        this.multipleTake = multipleTake;
    }

    public Coordinates getCurrent() {
        return current;
    }

    public void setCurrent(Coordinates current) {
        this.current = current;
    }

    //Sprawdzanie mozliwosci ruchu normalnego pionka
//    public boolean canMove(Coordinates piece) {
//        return (canBasicMove(piece) || canJump(piece));
//    }
//
//    public boolean canBasicMove(Coordinates piece) {
//        return (canBasicMoveLeft(piece) || canBasicMoveRight(piece));
//    }
//
//    public boolean canBasicMoveLeft(Coordinates piece) {
//        int changeY;
//        if (currentTurn == white) {
//            changeY = -1;
//        } else {
//            changeY = 1;
//        }
//        if (piece.getX() > 0 && piece.getY() + changeY < 8 && piece.getY() + changeY >= 0) {
//            return field[piece.getY() + changeY][piece.getX() - 1].getPlayer() == null;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean canBasicMoveRight(Coordinates piece) {
//        int changeY;
//        if (currentTurn == white) {
//            changeY = -1;
//        } else {
//            changeY = 1;
//        }
//        if (piece.getX() < 7 && piece.getY() + changeY < 8 && piece.getY() + changeY >= 0) {
//            return field[piece.getY() + changeY][piece.getX() + 1].getPlayer() == null;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean canJump(Coordinates piece) {
//        return (canJumpLeft(piece) || canJumpRight(piece));
//    }
//
//    public boolean canJumpLeft(Coordinates piece) {
//        int changeY;
//        String enemy;
//        if (currentTurn == white) {
//            changeY = -1;
//            enemy = black;
//        } else {
//            changeY = 1;
//            enemy = white;
//        }
//        if (piece.getX() > 1 && piece.getY() + changeY < 7 && piece.getY() + changeY >= 1) {
//            return field[piece.getY() + changeY][piece.getX() - 1].getPlayer() != null && field[piece.getY() + 2 * changeY][piece.getX() - 2].getPlayer() == null;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean canJumpRight(Coordinates piece) {
//        int changeY;
//        String enemy;
//        if (currentTurn == white) {
//            changeY = -1;
//            enemy = black;
//        } else {
//            changeY = 1;
//            enemy = white;
//        }
//        if (piece.getX() < 6 && piece.getY() + changeY < 7 && piece.getY() + changeY >= 1) {
//            return field[piece.getY() + changeY][piece.getX() + 1].getPlayer() != null && field[piece.getY() + 2 * changeY][piece.getX() + 2].getPlayer() == null;
//        } else {
//            return false;
//        }
//    }
//
//    public List<Coordinates> possibleMoves(Coordinates piece, boolean multipleTake) {
//        List<Coordinates> places = new ArrayList<>();
//        int changeY;
//
//        if (currentTurn == white) {
//            changeY = -1;
//        } else {
//            changeY = 1;
//        }
//        if (!multipleTake && this.canBasicMoveLeft(piece)) {
//            places.add(new Coordinates(piece.getX() - 1, piece.getY() + changeY));
//        }
//        if (!multipleTake && this.canBasicMoveRight(piece)) {
//            places.add(new Coordinates(piece.getX() + 1, piece.getY() + changeY));
//        }
//        if (this.canJumpLeft(piece)) {
//            places.add(new Coordinates(piece.getX() - 2, piece.getY() + 2 * changeY));
//        }
//        if (this.canJumpRight(piece)) {
//            places.add(new Coordinates(piece.getX() + 2, piece.getY() + 2 * changeY));
//        }
//
//        return places;
//    }

    // wersja knights
//    public List<Coordinates> commitMove(Coordinates start, Coordinates end) {
//        List<Coordinates> places = new ArrayList<>();
//        places.add(end);
//
//        field[end.getY()][end.getX()].setNewValues(field[start.getY()][start.getX()].getPlayer(), field[start.getY()][start.getX()].getPiece());
//        field[start.getY()][start.getX()].setNewValues();
//
//        multipleTake = abs(start.getX() - end.getX()) > 1 && this.canJump(end);
//
//        return places;
//    }

    // wersja checkers
//    public List<Coordinates> commitMove(Coordinates start, Coordinates end) {
//        List<Coordinates> places = new ArrayList<>();
//        places.add(end);
//
//        boolean movingToLeft = end.getX() < start.getX();
//        boolean movingUp = end.getY() < start.getY();
//        int i = start.getY(), j = start.getX();
//
//        do {
//            if (movingToLeft) {
//                j -= 1;
//            } else {
//                j += 1;
//            }
//            if (movingUp) {
//                i -= 1;
//            } else {
//                i += 1;
//            }
//            if (field[i][j].getPlayer() != null && field[i][j].getPlayer() != currentTurn) {
//                places.add(new Coordinates(j, i));
//                field[i][j].setNewValues();
//            }
//        } while (i != end.getY() && j != end.getX());
//
//        field[end.getY()][end.getX()].setNewValues(field[start.getY()][start.getX()].getPlayer(), field[start.getY()][start.getX()].getPiece());
//        field[start.getY()][start.getX()].setNewValues();
//
//        multipleTake = places.size() > 1 && this.canTake(end);
//
//        return places;
//    }

}
