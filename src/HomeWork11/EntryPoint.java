package HomeWork11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EntryPoint extends Application {

    @Override
    public void start(Stage stageMain) throws Exception{

        stageMain.setTitle("Welcome in to our shop");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view/MainScene.fxml"));
        Pane pane = (Pane)fxmlLoader.load();
        Scene scene = new Scene(pane);
        stageMain.setScene(scene);
        stageMain.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}