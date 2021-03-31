package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Utente implements Serializable {

    private String Id;

    private String Uname;

    private ArrayList<EasyEmail> eMailList;

    public Utente(String Id , String Uname , ArrayList<EasyEmail> eMailList){
        this.Id = Id;
        this.Uname = Uname;
        this.eMailList=eMailList;
    }



    public String getId(){
        return this.Id;
    }

    public String getUname(){
        return  this.Uname;
    }

    public ArrayList<EasyEmail> getEMailList(){
        return this.eMailList;
    }

    public void addMail(EasyEmail e) {
        this.eMailList.add(e);
    }
}
