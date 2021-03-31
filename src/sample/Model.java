package sample;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class Model{
    ArrayList<EasyEmail> eMailList = new ArrayList<>();

    ArrayList<Utente> Utentilist = new ArrayList<>();

    ObjectProperty<EasyEmail> currentEmail = new SimpleObjectProperty<>();

    public void addUtente(Utente u) {
        Utentilist.add(u);
    }

    public ArrayList<EasyEmail> geteMailList() {
        return eMailList;
    }

    public ArrayList<Utente> getUtentiList() {
        return Utentilist;
    }

    public Utente getUtente(String u) { //nuovo
        for(int i = 0; i<Utentilist.size(); i++) {
            if(Utentilist.get(i).getId().equals(u)) {
                return Utentilist.get(i);
            }
        }
        return null;
    }

    public EasyEmail getCurrentEmail() {
        return currentEmail.get();
    }

    public ObjectProperty<EasyEmail> currentEmailProperty() {
        return currentEmail;
    }

    public void setCurrentEmail(EasyEmail currentEmail) {
        this.currentEmail.set(currentEmail);
    }

    public void deleteCurrentemail(){
        this.geteMailList().remove(getCurrentEmail());
    }

    public void setUtentiList(ArrayList<Utente> l){
        this.Utentilist = l;
    }
}

