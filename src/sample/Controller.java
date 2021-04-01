package sample;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;



public class Controller{
    private Model model;

    public void start(Model model){
        this.model = model;
        try{
            Load();
        } catch (Exception e ) { //creo utenti fittizzi
            EasyEmail a = new EasyEmail(new String[]{"Luigi@mail"},"Mario@mail", "Object for Luigi", "Ciao luigi!");
            EasyEmail b = new EasyEmail(new String[]{"Andrea@mail"},"Mario@mail", "Object for Andrea", "Ciao andrea!");
            Utente Mario = new Utente("Mario@mail" , "Mario");
            Mario.addIMail(a);
            Mario.addIMail(b);
            model.addUtente(Mario);

            EasyEmail c = new EasyEmail(new String[]{"Mario@mail"},"Luigi@mail", "Object for Mario", "Ciao mario!");
            EasyEmail d = new EasyEmail(new String[]{"Andrea@mail"},"Luigi@mail", "Object for Andrea", "Ciao andrea!");
            Utente Luigi = new Utente("Luigi@mail" , "Luigi" );
            Luigi.addIMail(c);
            Luigi.addIMail(d);
            Luigi.addRMail(a);
            model.addUtente(Luigi);

            Utente Andrea = new Utente("Andrea@mail" , "Andrea");
            Andrea.addRMail(b);
            Andrea.addRMail(d);
            model.addUtente(Andrea);
        }
        try{
            ServerSocket s = new ServerSocket(8082);
            System.out.println("Socket Creato");
            while(true){
                Socket tube = s.accept();
                Runnable r = new ConnectionManager(this.model , tube);
                Thread t = new Thread(r);
                t.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Load() throws IOException, ClassNotFoundException {
        File f = new File("src/sample/Database.txt");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        model.setUtentiList((ArrayList<Utente>)in.readObject());
    }
}