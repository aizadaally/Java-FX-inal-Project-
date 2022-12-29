package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.logic.Board;
import main.java.logic.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static main.java.util.Constants.*;
import static main.java.util.Utils.isDarkTile;

/**
 * Contains various methods for visualizing UI elements, such as the chessboard
 */
public class BoardBuilder {

    private BoardBuilder(){}

    public static void drawBoard(Group uiBoard, Board b){

        uiBoard.getChildren().clear();

        for(int row = 0; row < 8; row++){

            Group uiRow = new Group();
            uiBoard.getChildren().add(uiRow);

            for(int col = 0; col < 8; col++){
                drawTile(uiBoard, row, col, b);
            }
        }
    }

    private static void drawTile(Group uiBoard, int row, int col, Board b){

        Rectangle rect = new Rectangle(
            (col * TILE_WIDTH) + BOARD_X_OFFSET,
            (row * TILE_HEIGHT) + BOARD_Y_OFFSET,
            TILE_WIDTH,
            TILE_HEIGHT
        );

        rect.setOnMouseReleased(event -> ClickHandler.handleTileClick(uiBoard, b, row, col));

        if(isDarkTile(row, col)){
            rect.setFill(Color.web("#DDDDDD"));
        }
        else{
            rect.setFill(Color.web("#333333"));
        }

        Group uiRow = (Group) uiBoard.getChildren().get(row);

        Group uiTile = new Group();

        uiTile.getChildren().add(rect);

        Piece piece = b.pieceAt(row, col);

        if(piece != null){
            ImageView pieceImageView = createPieceImageView(piece, row, col);
            uiTile.getChildren().add(pieceImageView);
        }

        uiRow.getChildren().add(uiTile);

    }

    private static ImageView createPieceImageView(Piece piece, int row, int col) {
        Image pieceImage = createPieceImage(piece);
        if(pieceImage == null) return null;
        ImageView pieceImageView = new ImageView(pieceImage);
        pieceImageView.setMouseTransparent(true);
        pieceImageView.setX(BOARD_X_OFFSET + PIECE_OFFSET + (TILE_WIDTH * col));
        pieceImageView.setY(BOARD_Y_OFFSET + PIECE_OFFSET + (TILE_HEIGHT * row));
        pieceImageView.setFitHeight(PIECE_HEIGHT);
        pieceImageView.setFitWidth(PIECE_WIDTH);
        return pieceImageView;
    }

    private static Image createPieceImage(Piece piece) {
        try {
            String path = "src/main/resources/images/" + piece.getColor() + piece.getType() + ".png";
            FileInputStream file = new FileInputStream(path);
            return new Image(file);
        }
        catch(FileNotFoundException fileNotFoundException){
            System.out.println("ERROR: " + piece.getColor() + " " + piece.getType() + " IMAGE NOT FOUND");
            return null;
        }
    }
}
