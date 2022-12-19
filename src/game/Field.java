package game;

public class Field {
    private String player;
    private String piece;

    public Field(String player, String piece) {
        this.player = player;
        this.piece = piece;
    }

    public Field() {
        this.player = null;
        this.piece = null;
    }

    public String getPlayer() {
        return player;
    }

    public String getPiece() {
        return piece;
    }

    public void setNewValues(String player, String piece) {
        this.player = player;
        this.piece = piece;
    }

    public void setNewValues(){
        this.player = null;
        this.piece = null;
    }

}
