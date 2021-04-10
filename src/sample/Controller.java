package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;



public class Controller{
    private Model model;

    @FXML
    private TextArea ServerText;

    public void start(Model model){
        this.model = model;

        ServerText.textProperty().bindBidirectional(model.outPutTextProperty());

        try{
            Load();
        } catch (Exception e ) { //creo utenti fittizzi
            //EasyEmail a = new EasyEmail(new String[]{"Luigi@mail"},"Mario@mail", "Object for Luigi", "Ciao luigi!");
           // EasyEmail b = new EasyEmail(new String[]{"Andrea@mail"},"Mario@mail", "Object for Andrea", "Ciao andrea!");
            Utente Mario = new Utente("Mario@mail" , "Mario");
           // Mario.addIMail(a);
           // Mario.addIMail(b);
            model.addUtente(Mario);

           // EasyEmail c = new EasyEmail(new String[]{"Mario@mail"},"Luigi@mail", "Object for Mario", "Ciao mario!");
            //EasyEmail d = new EasyEmail(new String[]{"Andrea@mail"},"Luigi@mail", "Object for Andrea", "Ciao andrea!");
            Utente Luigi = new Utente("Luigi@mail" , "Luigi" );
            //Luigi.addIMail(c);
           // Luigi.addIMail(d);
            //Luigi.addRMail(a);
            model.addUtente(Luigi);

            Utente Andrea = new Utente("Andrea@mail" , "Andrea");
           // Andrea.addRMail(b);
            //Andrea.addRMail(d);
            model.addUtente(Andrea);
        }
        Runnable r = new StartExec(this.model);
        Thread t = new Thread(r);
        t.start();
    }

    public void Load() throws IOException, ClassNotFoundException {
        File f = new File("src/sample/Database.txt");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        model.setUtentiList((ArrayList<Utente>)in.readObject());
    }
}