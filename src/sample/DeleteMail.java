package sample;

import java.io.Serializable;

public class DeleteMail extends Request implements Serializable {
    private EasyEmail e;
    private int location;
    // 0 : ricevute, 1 : inviate

    public DeleteMail(String u, EasyEmail e, int location) {
        super(2, u);
        this.e = e;
        this.location = location;
    }

    public EasyEmail getEE() {
        return this.e;
    }

    public int getLocation() {return this.location;}
}