package game;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates() {
        this.x = 0;
        this.y = 0;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setNew(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
