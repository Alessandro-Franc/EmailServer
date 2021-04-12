package sample;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class StartExec implements Runnable {
    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    static ServerSocket s;

    static boolean active = true;

    private Model model;

    public StartExec(Model m) {
        this.model = m;
    }

    public static void setActive() {
        active = false;
    }


    public static void stop() {
        setActive();
        try {
            String nomeHost = InetAddress.getLocalHost().getHostName();
            Socket s = new Socket(nomeHost, 8082);
            String send =  new Gson().toJson(new Request(5, "Mario@mail"));
            System.out.println(send);
            DataOutputStream emailOut = new DataOutputStream(s.getOutputStream());
            emailOut.writeUTF(send);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @Override
    public void run() {
        try {
            s = new ServerSocket(8082);
            System.out.println("Socket Creato");
            while (active) {
                Socket tube = s.accept();
                Runnable r = new ConnectionManager(this.model, tube);
                executor.execute(r);
                model.addOutPutText("Connessione eseguita");
            }
        }   catch (IOException e) {
            System.out.println("socket err");
            } finally {
                try {
                    s.close();
                    executor.shutdown();
                    System.out.println("Sto in final");
                } catch (Exception e){
                    System.out.println("Sto in catch");
                }
            }
        }
    }


