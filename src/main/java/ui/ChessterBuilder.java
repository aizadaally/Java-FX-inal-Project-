package main.java.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.util.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChessterBuilder {

    private ChessterBuilder(){}

    public static void drawChessterText(Group parent){
        Text text = new Text();
        text.setText("Hi, I'm Chesster!");
        text.setX(Constants.CHESSTER_X_OFFSET);
        text.setY(Constants.CHESSTER_Y_OFFSET);
        parent.getChildren().add(text);
    }

    public static void drawChesster(Group parent){
        try {
            FileInputStream file = new FileInputStream("src/main/resources/images/Chesster.png");
            Image image = new Image(file);
            ImageView chesster = new ImageView(image);
            chesster.setOnMouseReleased(event -> System.out.println("hi"));
            chesster.setX(Constants.CHESSTER_X_OFFSET);
            chesster.setY(Constants.CHESSTER_Y_OFFSET);
            chesster.setFitHeight(Constants.CHESSTER_HEIGHT);
            chesster.setFitWidth(Constants.CHESSTER_WIDTH);
            parent.getChildren().add(chesster);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("ERROR: CHESSTER IMAGE NOT FOUND");
        }
    }

}
