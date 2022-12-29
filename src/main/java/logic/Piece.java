package main.java.logic;

import lombok.Data;
import main.java.util.Enums.PieceColor;
import main.java.util.Enums.PieceType;

@Data
public class Piece {

    private Tile currentTile;

    private int row;
    private int col;

    private PieceType type;
    private PieceColor color;


    public Piece(PieceType type, PieceColor color, Tile tile) {
        this.setCurrentTile(tile);
        this.setType(type);
        this.setColor(color);
    }

    @Override
    public String toString() {
        return getColor() + " " + getType();
    }

}
