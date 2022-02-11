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

    public static boolean writeConfig(HashMap<String, String> config){
        try{
            FileWriter writer = new FileWriter("myconfig.txt");
            for(String s : config.keySet()){
                writer.write(s + ": " + config.get(s)+ "\n");
            }
            writer.flush();
            writer.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
