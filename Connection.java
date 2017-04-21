
package pkgswitch;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


class Connection implements Runnable {
    Socket clientSocket;
    BufferedReader din;
    OutputStreamWriter outWriter;
    public static String execv = "";
    public static String location = "";
    public String addr = "";
    public int i = 0;
    public String port;
    public int senity = 0;

    public Connection(Socket clientSocket, String p, String cmd, String path, String address ){
        this.execv = cmd;
        this.location = path;
        this.addr = address;
        port = p;
        try{
            this.clientSocket = clientSocket;
            din = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream(), "ASCII"));
            this.outWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            this.run();
        }catch(IOException e){
            System.out.println("Connection: " + e.getMessage());
        }   
    }
    
    @Override
    public void run(){
        if (senity==0){
            try{
                routeRequest(outWriter);
                outWriter.close();
                clientSocket.close();
            }catch(Exception e){
                System.out.println(e);
            }
        senity+=1;
        }
        }
 

    public void routeRequest(OutputStreamWriter outWriter) throws Exception{
        int R  = 34;
        char sla = (char) R;
        System.out.print(sla);
        System.out.print(".......");
        String unique = UUID.randomUUID().toString();
        unique = unique.substring(0, 5);
        execv += unique;
        execv += " ";
        execv += port;
        System.out.print("\n");
        System.out.print(execv);
        System.out.print("\n");
        Process p = Runtime.getRuntime().exec(sh);
        try{ TimeUnit.SECONDS.sleep(1); } catch (Exception e) {}
        path += unique;
        BufferedReader br = new BufferedReader(new FileReader(location));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        System.out.print("\n");
        String route = line.toString();
        System.out.print(route);
        System.out.print("\n");
        try{
                TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {System.out.print(e);}
        String loc ="";
        loc += addr;
        loc += port;
        loc += "/";
        loc += route;
        if (i == 0){
        outWriter.write("HTTP/1.1 302 Found\r\n");        
        outWriter.write("Content-type: text/plain\r\n");
        //outWriter.write("Server: vinit\r\n");           
        outWriter.write("Location: "+loc+"\r\n");
        outWriter.write("\r\n\r\n");
        i += 1;
        }
        
    }
}
