package sample;

import java.io.Serializable;

public class SendMail extends Request implements Serializable {
    private EasyEmail e;

    public SendMail(String u, EasyEmail e) {
        super(1, u);
        this.e = e;
    }

    public EasyEmail getEE() {
        return this.e;
    }
}
