import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    buffer buf;
    static int counter =0;
    String filename;
    static String largestPrime;
    public Consumer(buffer buf,String filename) {
        this.buf = buf;
        this.filename= filename;
    }

    public void run() {
            String item;
            try {
                FileWriter fw= new FileWriter(filename+".txt");
                while (true)
                {
                    if(buf.is_finished && buf.element==0)
                    {
                        fw.close();
                        break;
                    }
                    else {
                    item = (String) buf.consume();
                    fw.write(String.valueOf(item) + " , ");
                    largestPrime =item;
                    counter++;
                }
                }
            }
      catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
