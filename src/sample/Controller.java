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
            EasyEmail a = new EasyEmail("Luigi@mail" , "Object for Luigi", "Ciao luigi!");
            EasyEmail b = new EasyEmail("Andrea@mail" , "Object for Andrea", "Ciao andrea!");
            ArrayList<EasyEmail> MarioList = new ArrayList<>();
            MarioList.add(a);
            MarioList.add(b);
            model.addUtente(new Utente("Mario@mail" , "Mario" , MarioList));

            EasyEmail c = new EasyEmail("Mario@mail" , "Object for Mario", "Ciao mario!");
            ArrayList<EasyEmail> LuigiList = new ArrayList<>();
            LuigiList.add(c);
            LuigiList.add(b);
            model.addUtente(new Utente("Luigi@mail" , "Luigi" , LuigiList));

            ArrayList<EasyEmail> AndreaList = new ArrayList<>();
            AndreaList.add(a);
            AndreaList.add(c);
            model.addUtente(new Utente("Andrea@mail" , "Andrea" , MarioList));
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
        File f = new File("B:\\Desktop\\scuola\\prog 3\\progetto\\wetransfer-9c7849\\EmailServer\\src\\sample\\Database.txt");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        model.setUtentiList((ArrayList<Utente>)in.readObject());
    }
}