package sample;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ConnectionManager implements Runnable{
    private Model model;
    private Socket socket;
    private Utente utente;

    public ConnectionManager(Model m , Socket socket){
        this.model=m;
        this.socket = socket;
    }

    /*@Override
    public void run() {
        int request;
        try{
            try{
                System.out.println("Collegamento effettuato");
                InputStream Request = new DataInputStream(socket.getInputStream());
                request = Request.read();
                utente = model.getUtentiList().get(0);
                switch(request){
                    case 0 :
                        //Invio la lista delle email
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(utente.getEMailList());
                        for(int i=0; i<utente.getEMailList().size() ; i++){
                            System.out.println(utente.getEMailList().get(i).getObject());
                        }
                        break;
                    case 1:
                        //aggiungo email alla lista
                        ObjectInputStream emailIn =  new ObjectInputStream(socket.getInputStream());
                        System.out.println("Email ricevuta");
                        try{
                            EasyEmail m = ((EasyEmail) emailIn.readObject());
                            utente.getEMailList().add(m);
                        }catch(ClassNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        break;
                }
            }finally{
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }*/

    @Override
    public void run() { //rimandargli run intero
        try{
            try{
                System.out.println("Collegamento effettuato");
                DataInputStream in =  new DataInputStream(socket.getInputStream());
                String request = in.readUTF();
                System.out.println(request);
                Request f = new Gson().fromJson(request, Request.class);
                int idR = f.getId();
                String u = f.getUtente();
                utente = model.getUtente(u);
                switch (idR) {
                    case 0:
                        //Invio la lista delle email
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(utente.getEMailList());
                        for(int i=0; i<utente.getEMailList().size() ; i++){
                            System.out.println(utente.getEMailList().get(i).getObject());
                        }
                        break;
                    case 1:
                        //aggiungo email alla lista
                        SendMail e = new Gson().fromJson(request, SendMail.class);
                        EasyEmail m = e.getEE();
                        String dest = m.getDestination();
                        Utente destinatario = model.getUtente(dest);
                        if(destinatario==null) {
                            System.out.println("Email con destinatario inesistente");
                        }
                        else {
                            System.out.println("Email ricevuta");
                            utente.getEMailList().add(m);
                            destinatario.getEMailList().add(m);
                            Save();
                        }
                        break;
                    default:
                        break;
                }
            }finally{
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Save() throws IOException {
        File f = new File("B:\\Desktop\\scuola\\prog 3\\progetto\\wetransfer-9c7849\\EmailServer\\src\\sample\\Database.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(model.getUtentiList());
    }
}