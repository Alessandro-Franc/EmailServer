package sample;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("ServerView.fxml"));
        root.setCenter(serverLoader.load());
        Controller serverController = serverLoader.getController();

        Model m = new Model();
        /*EasyEmail a = new EasyEmail("a" , "a" , "a");
        ArrayList<EasyEmail> eMailList = new ArrayList<>();
        eMailList.add(a);
        m.addUtente(new Utente(1 , "Mario" , eMailList));*/
        serverController.start(m);
        //String r = new Gson().toJson(new SendMail(1, a));
        //System.out.println(r);
    }

    public static void main(String[] args) {
        /*EasyEmail a = new EasyEmail("a" , "a" , "a");
        String r = new Gson().toJson(new SendMail(1, a));
        System.out.println(r);
        Request f = new Gson().fromJson(r, Request.class);
        int idR = f.getId();
        int utente = f.getUtente();
        switch (idR) {
            case 1:
                SendMail e = new Gson().fromJson(r, SendMail.class);
                EasyEmail b = e.getEE();

        }*/
        launch(args);
    }
}