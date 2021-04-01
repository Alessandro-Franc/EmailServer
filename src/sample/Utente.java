package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Utente implements Serializable {

    private String Id;

    private String Uname;

    private ArrayList<EasyEmail> ReMailList;

    private ArrayList<EasyEmail> IeMailList;

    private ArrayList<ArrayList<EasyEmail>> eMailList;

    public Utente(String Id , String Uname){
        this.Id = Id;
        this.Uname = Uname;
        this.ReMailList = new ArrayList<>();
        this.IeMailList = new ArrayList<>();
        this.eMailList = new ArrayList<>();
        this.eMailList.add(ReMailList);
        this.eMailList.add(IeMailList);
    }
    public String getId(){
        return this.Id;
    }

    public String getUname(){
        return  this.Uname;
    }

    public ArrayList<ArrayList<EasyEmail>> getEMailList(){
        return this.eMailList;
    }
    public ArrayList<EasyEmail> getREmailList(){
        return this.ReMailList;
    }

    public ArrayList<EasyEmail> getIEmailList(){
        return this.IeMailList;
    }

    public void addRMail(EasyEmail e) {
        getREmailList().add(e);
    }

    public void addIMail(EasyEmail e) {
        getIEmailList().add(e);
    }
}
