package sample;

import java.io.Serializable;

public class Request implements Serializable {
    private int id;
    private String Utente;

    public Request (int id, String u) {
        this.id = id;
        this.Utente = u;
    }

    public int getId() {
        return this.id;
    }

    public String getUtente() {
        return this.Utente;
    }
}
