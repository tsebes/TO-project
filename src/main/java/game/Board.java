package game;

public class Board {

    public static final int TILE_COUNT = 8;
    protected final Field[][] fields;
    private boolean multipleTake = false;
    private Coordinates current;

    public Board() {
        fields = new Field[TILE_COUNT][TILE_COUNT];
    }

    public Board(Field[][] fields) {
        this.fields = fields;
    }

    public Field getField(Coordinates place) {
        return fields[place.x()][place.y()];
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
}
