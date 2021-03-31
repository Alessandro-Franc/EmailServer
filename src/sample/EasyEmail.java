package sample;

import java.io.Serializable;

public class EasyEmail implements Serializable {
    private String destination;
    private String Mitt;
    private String object;
    private String eText;


    public EasyEmail(String destination, String Mitt, String obj , String eText){
        this.destination = destination;
        this.Mitt = Mitt;
        this.object=obj;
        this.eText = eText;
    }

    public String getObject(){
        return object;
    }

    public String geteText(){
        return eText;
    }

    public String getDestination(){
        return destination;
    }

    public String getMitt(){
        return Mitt;
    }
}
