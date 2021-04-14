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
                        model.addOutPutText("Richiesta da: " + u + ", Lista email inviata");
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
                        boolean success = false;
                        String err = "";
                        int counter = 0;
                        SendMail e = new Gson().fromJson(packet, SendMail.class);
                        EasyEmail m = e.getEE();
                        String[] dest = m.getDestination();
                        for(int i = 0; i< dest.length; i++) {
                            Utente destinatario = model.getUtente(dest[i]);
                            if(destinatario==null) {
                                err += "Destinatario numero "+(i+1)+" inesistente\n";
                                model.addOutPutText("Richiesta da:" + u + err);
                            }
                            else {
                                System.out.println("Email ricevuta");
                                destinatario.getREmailList().add(m);
                                counter++;
                                success = true;
                                model.addOutPutText("Richiesta da: " + u + ", Email Ricevuta");
                            }
                        }
                        err += "Email inviata con successo a "+counter+" destinatari";
                        model.addOutPutText("Richiesta da: " + u +" "+ err);
                        System.out.println(err);
                        DataOutputStream response = new DataOutputStream(socket.getOutputStream());
                        response.writeUTF(err);
                        if(success) utente.getIEmailList().add(m);
                        Save();
                        break;
                    case 2:
                        //delete mail
                        Boolean b = true;
                        DeleteMail d = new Gson().fromJson(packet, DeleteMail.class);
                        EasyEmail m2 = d.getEE();
                        DataOutputStream result = new DataOutputStream(socket.getOutputStream());
                        String res = "";
                        switch(d.getLocation()) {
                            case 0:
                                for (int i = 0; i < utente.getREmailList().size(); i++) {
                                    if (utente.getREmailList().get(i).Equals(m2) && b) {
                                        utente.getREmailList().remove(i);
                                        res += "email rimossa dalla lista ricevute di " + u;
                                        model.addOutPutText("Richiesta da: " + u + res);
                                        //System.out.println("email rimossa dalla lista ricevute di " + u);
                                        b = false;
                                    }
                                }
                            break;
                            case 1:
                                for (int i = 0; i < utente.getIEmailList().size(); i++) {
                                    if (utente.getIEmailList().get(i).Equals(m2) && b) {
                                        utente.getIEmailList().remove(i);
                                        res += "email rimossa dalla lista inviate di " + u;
                                        model.addOutPutText("Richiesta da: " + u + res);
                                        System.out.println("email rimossa dalla lista inviate di " + u);
                                        b = false;
                                    }
                                }
                        }
                        if(res.equals("")) res = "email da eliminare non trovata";
                        model.addOutPutText("Richiesta da: " + u + res);
                        result.writeUTF(res);
                        Save();
                    case 5:
                        System.out.println("chiudo server");
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