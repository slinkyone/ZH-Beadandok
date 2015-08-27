import router.*;
import encoding.*;
import java.io.Console;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class Simulation {

    //ket parancssori parametert var: a bemeneti es kimeneti fajlneveket
    public static void main(String[] args) {
        List<String> traces = new LinkedList<>();
        if (args.length != 2) {
            return;
        }
        String infile = args[0];
        String outfile = args[1];
        Node n = Network.createNetwork();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(infile));
            while (br.ready()) {
                String line = br.readLine();
                int atCount = line.length() - line.replace("@", "").length();
                int barCount = line.length() - line.replace("|", "").length();
                    if (atCount != 1 || barCount != 1) {
                        continue;
                    }
                String[] sub = line.split("\\|");
                String message = sub[0];
                String ip = sub[1];
                    try{
                        Message msg = new Message();
                        msg.fromText(message);
                        Packet<Message> p = new Packet<Message>(msg,IPAddress.fromString(ip));
                        String str = n.tracePacket(p);
                        traces.add(str);

                    } catch (NullPointerException e){
                        System.err.println("Hibás üzenet! ");
                    } catch (IPFormatException e) {
                        System.err.println("Hibás IP cím! ");
                    }
                }
            
        }catch (FileNotFoundException e) {
            System.err.println("Nem létezik a megadott nevű bemeneti file.");
            return;
        }catch (IOException e) {
            System.err.println("Egyéb I/O hiba.");
            return;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Hiba az olvasó lezárása közben.");
                }
            }
        }

        PrintWriter pw = null;
            try {
            pw = new PrintWriter(outfile);
            for (String str : traces) {
                    pw.println(str);
                }
        }
        catch (IOException e) {
            System.err.println("Hiba az irás közben.");
        }
            finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
