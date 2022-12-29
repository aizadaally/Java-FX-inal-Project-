package main.java.logic;

import lombok.Data;

/**
 * A single tile on a chessboard. Its only field is a reference to a chess piece object.
 * No constructors: instead, this class gets instantiated from JSON by Gson.
 */
@Data
public class Tile {

    private Tile(){}

    private boolean highlighted;

    private Piece piece;

    public boolean isEmpty(){
        return piece == null;
    }

    @Override
    public String toString() {
        return piece != null ? piece.toString() : null;
    }
}
