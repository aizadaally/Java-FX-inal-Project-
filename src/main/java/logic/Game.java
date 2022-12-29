package main.java.logic;

import lombok.Data;
import main.java.util.Enums;

@Data
public class Game {

    private Board board;

    private Player white;
    private Player black;

    public Game(Board b) {
        setWhite(new Player());
        setBlack(new Player());
        setBoard(b);
    }

    public void movePiece(Tile source, Tile destination){

        if(destination.getPiece().getColor().equals(Enums.PieceColor.Black)){
            getBlack().getDeadPieces().add(destination.getPiece());
        }
        else{
            getWhite().getDeadPieces().add(destination.getPiece());
        }

        // pass by reference I hope?
        destination.setPiece(source.getPiece());
        source.setPiece(null);
    }
}
