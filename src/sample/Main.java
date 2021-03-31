package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("ServerView.fxml"));
        root.setCenter(serverLoader.load());
        Controller serverController = serverLoader.getController();

        Model m = new Model();
        serverController.start(m);
    }

    public static void main(String[] args) {
        launch(args);
    }
}