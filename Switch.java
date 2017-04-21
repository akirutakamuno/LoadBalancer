package pkgswitch;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class Switch {
    
   public static String port = "800";
   public static int p = 0;
   public static String addr = "";
    
    
  public static void main(String[] args )
   {  
   
      int l = args.length - 1;
      this.addr = args[l];
      
      try
      {  
          
         InetAddress bindAddr = InetAddress.getByName(addr);
         int i = 1;
         ServerSocket s = new ServerSocket(80, 1, bindAddr);
         while (true)
         {  
 
            Socket incoming = s.accept();
            p += 1;
            port += Integer.toString(p);
            Runnable r = new Connection(incoming, port);
            Thread t = new Thread(r);
            t.start();
            int len = port.length() - 1;
            port = port.substring(0, len);
            i++;
            try{
                TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {System.out.print(e);}
         }
      }
      catch (Exception e)
      {  
         e.printStackTrace();
      }
   }
}
