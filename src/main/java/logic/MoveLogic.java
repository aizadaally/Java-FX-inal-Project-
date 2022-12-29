package main.java.logic;

import main.java.util.Enums.PieceColor;

import static main.java.util.Utils.outOfBounds;

public class MoveLogic {

    private MoveLogic(){}

    private static final int[][] knightOffsets = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
    private static final int[][] bishopOffsets = {{1,1},{1,-1},{-1,1},{-1,-1}};
    private static final int[][] rookOffsets = {{0,1},{0,-1},{1,0},{-1,0}};
    private static final int[][] royalOffsets = {{1,1},{1,-1},{-1,1},{-1,-1},{0,1},{0,-1},{1,0},{-1,0}};
    private static final int whiteDirection = -1;
    private static final int blackDirection = 1;

    // recursive method to scan a straight line across the board for rooks, bishops, and queens
    private static void scan(CoordinateList coordinates, Board b, int row, int col, int rowOffset, int colOffset, PieceColor pieceColor) {

        // base case: line has exited the board
        if(outOfBounds(row + rowOffset, col + colOffset) ) {
            return;
        }

        // base case: there's a piece in the way
        if(b.hasPieceAt(row + rowOffset, col + colOffset)){
            if(b.pieceAt(row + rowOffset, col + colOffset).getColor() != pieceColor) {
                coordinates.add(row + rowOffset, col + colOffset);
            }
            return;
        }

        // empty tile? keep going
        coordinates.add(row + rowOffset, col + colOffset);
        scan(coordinates, b, row + rowOffset, col + colOffset, rowOffset, colOffset, pieceColor);

    }


    public static CoordinateList validPawnMoves(Board b, int row, int col) {

        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        int direction = pieceColor == PieceColor.Black ? blackDirection : whiteDirection;
        int finalRow = pieceColor == PieceColor.Black ? 7 : 0;
        int starterRow = pieceColor == PieceColor.Black ? 1 : 6;

        CoordinateList retval = new CoordinateList();

        if(row == finalRow) return retval; // will never happen once I add promotion

        if(!b.hasPieceAt(row + direction, col)){
            retval.add(row + direction, col);
        }
        if(row == starterRow && !b.hasPieceAt(row + direction, col) && !b.hasPieceAt(row + direction*2, col)){
            retval.add(row + direction*2, col);
        }
        if(col != 7 && b.hasPieceAt(row + direction, col + 1) && b.pieceAt(row + direction, col + 1).getColor() != pieceColor){
            retval.add(row + direction, col + 1);
        }
        if(col != 0 && b.hasPieceAt(row + direction, col - 1) && b.pieceAt(row + direction, col - 1).getColor() != pieceColor){
            retval.add(row + direction, col - 1);
        }
        return retval;
    }


    public static CoordinateList validKnightMoves(Board b, int row, int col){
        CoordinateList retval = new CoordinateList();

        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        for(int[] knightOffset: knightOffsets){
            int destRow = row + knightOffset[0];
            int destCol = col + knightOffset[1];
            if(outOfBounds(destRow, destCol)) continue;
            if(b.tileAt(destRow, destCol).isEmpty() || b.pieceAt(destRow, destCol).getColor() != pieceColor) {
                retval.add(destRow, destCol);
            }
        }
        return retval;
    }

    public static CoordinateList validKingMoves(Board b, int row, int col){
        CoordinateList retval = new CoordinateList();
        PieceColor pieceColor = b.pieceAt(row, col).getColor();

        for (int[] kingOffset : royalOffsets) {
            int destRow = row + kingOffset[0];
            int destCol = col + kingOffset[1];

            if (outOfBounds(destRow, destCol)) continue;

            if ((b.tileAt(destRow, destCol).isEmpty() || b.pieceAt(destRow, destCol).getColor() != pieceColor)) {
                // TODO make sure this dest tile isn't dangerous for our dear leader
                retval.add(row + kingOffset[0], col + kingOffset[1]);
            }
        }
        return retval;
    }

    public static CoordinateList validQueenMoves(Board b, int row, int col) {
        return validSliderMoves(b, row, col, royalOffsets);
    }
    public static CoordinateList validRookMoves(Board b, int row, int col){
        return validSliderMoves(b, row, col, rookOffsets);
    }
    public static CoordinateList validBishopMoves(Board b, int row, int col){
        return validSliderMoves(b, row, col, bishopOffsets);
    }

    public static CoordinateList validSliderMoves(Board b, int row, int col, int[][] typeOffsets){
        PieceColor pieceColor = b.pieceAt(row, col).getColor();
        CoordinateList validMoves = new CoordinateList();

        for(int[] xyOffset: typeOffsets) {
            scan(validMoves, b, row, col, xyOffset[0], xyOffset[1], pieceColor);
        }
        return validMoves;
    }

}
