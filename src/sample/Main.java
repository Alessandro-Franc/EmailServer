package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void stop() {
        StartExec.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}