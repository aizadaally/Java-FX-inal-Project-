package main.java.ui;


import javafx.scene.Group;
import main.java.logic.Board;

import static main.java.ui.BoardBuilder.drawBoard;
import static main.java.ui.HighlightBuilder.highlightValidMoves;

public class ClickHandler {

    private ClickHandler(){}

    public static void handleTileClick(Group uiBoard, Board b, int row, int col){
        if(b.tileAt(row, col).isHighlighted()){
            int moveResult = b.move(row, col);
            if(moveResult == 0){
                drawBoard(uiBoard, b);
                b.setPieceToMove(null);
                b.dehighlight();
            }

        }
        else{
            b.dehighlight();
            drawBoard(uiBoard, b);
            if(b.hasPieceAt(row, col)) {
                highlightValidMoves(uiBoard, b, row, col);
            }
            b.setPieceToMove(b.pieceAt(row, col)); // could be null
        }
    }

}
