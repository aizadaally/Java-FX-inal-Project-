package main.java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.logic.Board;
import main.java.logic.Game;
import main.java.ui.BoardBuilder;
import main.java.ui.ChessterBuilder;
import main.java.ui.ListBuilder;
import main.java.util.JSONParser;

import static main.java.util.Constants.SCENE_HEIGHT;
import static main.java.util.Constants.SCENE_WIDTH;

public class GameApplication extends javafx.application.Application {

    /**
     * The main method of this application: creates objects, maintains state, displays UI elements
     * @param stage auto-generated JavaFX Stage object for displaying a custom scene
     */
    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        String rawBoard = JSONParser.readJSONToString("initialLayout");
        Board b = JSONParser.convertStringToObject(rawBoard);

        new Game(b);

        BoardBuilder.drawBoard(root, b);

        ListBuilder.buildList(root);

        ChessterBuilder.drawChesster(root);
        ChessterBuilder.drawChessterText(root);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

}
