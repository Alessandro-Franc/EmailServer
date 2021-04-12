package sample;

import java.io.Serializable;

public class Request implements Serializable {
    private int Rtype;
    private String Utente;

    public Request (int Rtype, String u) {
        this.Rtype = Rtype;
        this.Utente = u;
    }

    public int getRtype() {
        return this.Rtype;
    }

    public String getUtente() {
        return this.Utente;
    }
}
