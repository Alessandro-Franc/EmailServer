package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
