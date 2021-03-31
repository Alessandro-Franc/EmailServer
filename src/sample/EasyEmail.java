package sample;

import java.io.Serializable;

public class EasyEmail implements Serializable{
    private String object;
    private String eText;
    private String destination;

    public EasyEmail(String destination , String obj , String eText){
        this.destination = destination;
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
}
