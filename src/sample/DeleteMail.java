package sample;

import java.io.Serializable;

public class DeleteMail extends Request implements Serializable {
    private EasyEmail e;

    public DeleteMail(String u, EasyEmail e) {
        super(2, u);
        this.e = e;
    }

    public EasyEmail getEE() {
        return this.e;
    }
}