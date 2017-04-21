package pkgswitch;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

public class Switch {
    
   public static String port = "";
   public static int p = 0;
   public static String addr = "";
   public static String cmd = "";
   public static String path = "";
   public static String[] addrbook = new String[20];
  
  /**
 * @ sudo java -jar Switch.jar "/path/to/addr/file" "path to the single thread output file, 
 *    for the secure string" "command to activate the single thread"  "the server listening addr" "starting port"
 *    for the first single thread.
 * 
 */
 
  public static void main(String[] args )
   {  
   
      int l = args.length - 1;
      int y = 0;
      try (BufferedReader br = new BufferedReader(new FileReader(args[l-4]))) {
                String line;
                while ((line = br.readLine()) != null) {
                   this.addrbook[y] = line;
                   y += 1;
           }
      }
      this.path = args[l-3]
      this.cmd = args[l-2]
      this.addr = args[l-1]
      this.port = args[l]
      
      try
        {  
          
         InetAddress bindAddr = InetAddress.getByName(addr);
         int i = 0;
         ServerSocket s = new ServerSocket(80, 1, bindAddr);
         while (true)
         {  
 
            Socket incoming = s.accept();
            p += 1;
            port += Integer.toString(p);
            Runnable Single = new Connection(incoming, port, cmd, path, addrbook[i]);
            Thread Serve = new Thread(Single);
            Serve.start();
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


