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
                String packet = in.readUTF();
                System.out.println(packet);
                //spacchetto il gson
                Request f = new Gson().fromJson(packet, Request.class);
                //identifico il tipo di richiesta
                int Rtype = f.getRtype();
                //identifico l'utente che comunica col server
                String u = f.getUtente();
                utente = model.getUtente(u);
                switch (Rtype) {
                    case 0:
                        //Invio la lista delle email
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeObject(utente.getEMailList());
                        System.out.println("Email Ricevute");
                        for(int i=0; i<utente.getREmailList().size() ; i++){
                            System.out.println(utente.getREmailList().get(i).getObject());
                        }
                        System.out.println("Email Inviate");
                        for(int i=0; i<utente.getIEmailList().size() ; i++){
                            System.out.println(utente.getIEmailList().get(i).getObject());
                        }
                        break;
                    case 1:
                        //aggiungo email alla lista
                        SendMail e = new Gson().fromJson(packet, SendMail.class);
                        EasyEmail m = e.getEE();
                        String[] dest = m.getDestination();
                        for(int i = 0; i< dest.length; i++) {
                            Utente destinatario = model.getUtente(dest[i]);
                            if(destinatario==null) {
                                System.out.println("Destinatario numero "+(i+1)+" inesistente");
                            }
                            else {
                                System.out.println("Email ricevuta");
                                destinatario.getREmailList().add(m);
                            }
                        }
                        utente.getIEmailList().add(m);
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