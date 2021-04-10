package sample;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class StartExec implements Runnable{
    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    private Model model;

    public  StartExec(Model m){
        this.model = m;
    }
    @Override
    public void run() {
        try{
            ServerSocket s = new ServerSocket(8082);
            System.out.println("Socket Creato");
            while(true){
                Socket tube = s.accept();
                Runnable r = new ConnectionManager(this.model , tube);
                executor.execute(r);
                model.addOutPutText("Connessione eseguita");
                //Thread t = new Thread(r);
               // t.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
