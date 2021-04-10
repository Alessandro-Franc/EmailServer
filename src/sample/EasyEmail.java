package sample;

import java.io.Serializable;

public class EasyEmail implements Serializable {
    private String[] destination;
    private String Mitt;
    private String object;
    private String eText;
    private String data;


    public EasyEmail(String[] destination, String Mitt, String obj , String eText, String data){
        this.destination = destination;
        this.Mitt = Mitt;
        this.object=obj;
        this.eText = eText;
        this.data = data;
    }

    public String getObject(){
        return object;
    }

    public String geteText(){
        return eText;
    }

    public String[] getDestination(){
        return destination;
    }

    public String getMitt(){
        return Mitt;
    }

    public String getData() {return data;}

    public boolean Equals(EasyEmail m) {
        if(this.toString().equals(m.toString())) return true;
        else return false;
    }

    @Override
    public String toString() {
        String dest = "";
        for(int i = 0; i<this.destination.length; i++) {
            dest += destination[i];
            dest += "; ";
        }
        return "Mitt: "+this.getMitt() + " dest: " + dest + ", Object: "+ this.getObject() + ", eText: "+this.geteText();
    }
}
