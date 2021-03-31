package sample;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;


public class ConnectionManager implements Runnable{
    private Model model;
    private Socket socket;
    private Utente utente;

    public ConnectionManager(Model m , Socket socket){
        this.model=m;
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            try{
                System.out.println("Collegamento effettuato");
                DataInputStream in =  new DataInputStream(socket.getInputStream());
                String request = in.readUTF();
                System.out.println(request);
                Request f = new Gson().fromJson(request, Request.class);
                int Rtype = f.getRtype();
                String u = f.getUtente();
                utente = model.getUtente(u);
                switch (Rtype) {
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
                        String[] dest = m.getDestination();
                        for(int i = 0; i< dest.length; i++) {
                            Utente destinatario = model.getUtente(dest[i]);
                            if(destinatario==null) {
                                System.out.println("Destinatario numero "+(i+1)+" inesistente");
                            }
                            else {
                                System.out.println("Email ricevuta");
                                destinatario.getEMailList().add(m);
                            }
                        }
                        utente.getEMailList().add(m);
                        Save();
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
        File f = new File("src/sample/Database.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(model.getUtentiList());
    }
}