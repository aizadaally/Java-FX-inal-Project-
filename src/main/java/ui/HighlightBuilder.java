package main.java.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import main.java.logic.Board;
import main.java.logic.Coordinate;
import main.java.logic.CoordinateList;
import main.java.util.Enums.PieceType;

import static main.java.logic.MoveLogic.*;

public class HighlightBuilder {


    private HighlightBuilder(){}

    public static void highlightValidMoves(Group uiBoard, Board b, int row, int col) {

        PieceType pieceType = b.pieceAt(row, col).getType();

        switch (pieceType) {
            case Pawn ->    highlightAll(validPawnMoves  (b, row, col), uiBoard, b);
            case Bishop ->  highlightAll(validBishopMoves(b, row, col), uiBoard, b);
            case Knight ->  highlightAll(validKnightMoves(b, row, col), uiBoard, b);
            case Queen ->   highlightAll(validQueenMoves (b, row, col), uiBoard, b);
            case King ->    highlightAll(validKingMoves  (b, row, col), uiBoard, b);
            case Rook ->    highlightAll(validRookMoves  (b, row, col), uiBoard, b);
        }
    }

    private static void highlightAll(CoordinateList validMoves, Group uiBoard, Board b) {
        for(Coordinate coord: validMoves) {
            highlight(uiBoard, b, coord.getRow(), coord.getCol());
        }
    }

    private static void highlight(Group uiBoard, Board b, int row, int col) {

        b.tileAt(row, col).setHighlighted(true);

        Rectangle rect = (Rectangle) ((Group) ((Group) uiBoard.getChildren().get(row)).getChildren().get(col)).getChildren().get(0);
        rect.setStroke(Color.web("#888833"));
        rect.setStrokeWidth(5);
        rect.setStrokeType(StrokeType.INSIDE);
    }

}
