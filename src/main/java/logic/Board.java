package main.java.logic;

import lombok.Getter;
import lombok.Setter;
import main.java.util.Enums.PieceColor;
import main.java.util.Enums.PieceType;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Class that represents an 8x8 board state of tiles and pieces.
 * Instantiated via Gson reading JSON, never directly.
 */

public class Board {

    @Getter
    @Setter
    private Piece pieceToMove;

    private List<List<Tile>> tiles;

    public List<Tile> rowAt(int row) {
        return this.tiles.get(row);
    }

    public Tile tileAt(int row, int col) {
        return this.tiles.get(row).get(col);
    }

    public Piece pieceAt(int row, int col) {
        return this.tiles.get(row).get(col).getPiece();
    }

    public boolean hasPieceAt(int row, int col){
        return pieceAt(row, col) != null;
    }

    public boolean hasBlackPieceAt(int row, int col){
        return pieceAt(row, col) != null && pieceAt(row, col).getColor() == PieceColor.Black;
    }

    public boolean hasWhitePieceAt(int row, int col){
        return pieceAt(row, col) != null && pieceAt(row, col).getColor() == PieceColor.White;
    }

    public int move(int destRow, int destCol) {

        int srcRow = pieceToMove.getRow();
        int srcCol = pieceToMove.getCol();

        if(pieceToMove.getType() == PieceType.Pawn &&
                ((pieceToMove.getColor() == PieceColor.Black && destRow == 7) ||
                (pieceToMove.getColor() == PieceColor.White && destRow == 0))){
            // TODO PROMOTION
            //PieceType pieceType = promptUserForChoice();
            //pieceToMove.setType(pieceType);
        }

        tileAt(destRow, destCol).setPiece(pieceToMove);
        tileAt(srcRow, srcCol).setPiece(null);
        pieceToMove.setRow(destRow);
        pieceToMove.setCol(destCol);

        try (Socket s = new Socket("127.0.0.1", 34001)) {
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            System.out.println("Sending move data to server.");
            out.println(pieceToMove.getColor() + " " + pieceToMove.getType() + " moved to " + destRow + destCol + "\n");
        }
        catch (IOException ioe) {
            System.out.println("Server connection could not be established.");
        }


        return 0;
    }

    public void dehighlight(){
        for(List<Tile> row: tiles){
            for(Tile tile: row){
                tile.setHighlighted(false);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        tiles.forEach(row -> representation.append(row + "\n"));
        return representation.toString();
    }


}
