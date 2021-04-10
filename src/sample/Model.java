package sample;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Model{
    //lista delle email
    ArrayList<EasyEmail> eMailList = new ArrayList<>();

    //lista degli utenti
    ArrayList<Utente> Utentilist = new ArrayList<>();

    ObjectProperty<EasyEmail> currentEmail = new SimpleObjectProperty<>();

    //Stringa per il promp sul server layout
    StringProperty OutPutText = new SimpleStringProperty("");

    public String getOutPutText() {
        return OutPutText.get();
    }

    public StringProperty outPutTextProperty() {
        return OutPutText;
    }

    public void setOutPutText(String outPutText) {
        this.OutPutText.set(outPutText);
    }

   public void addOutPutText (String s){
        setOutPutText(getOutPutText() + s + "\n");
   }

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

