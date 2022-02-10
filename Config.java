import java.util.*;
import java.io.*;

public class Config {
    static HashMap<String, String> config = new HashMap<>();
    
    public static void readConfig(){
        try {
            Scanner read = new Scanner(new File("myconfig.txt"));
            for(;read.hasNextLine();){
                StringTokenizer tokens = new StringTokenizer(read.nextLine(),": ");
                config.put(tokens.nextToken(), tokens.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
