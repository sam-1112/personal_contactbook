import java.util.*;
import java.io.*;

public class Catalog {
    private String Catalog_Name;
    static ArrayList<Catalog> catalog = new ArrayList<>();

    public Catalog(String name){
        this.Catalog_Name = name;
    }

    public static void readCatalog(){
        try {
            Scanner read = new Scanner(new File("catalog.txt"));
            while(read.hasNextLine()){
                Catalog name = new Catalog(read.nextLine().trim());
                catalog.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean writeCatalog(Catalog cat){
        try{
            FileWriter writer = new FileWriter("catalog.txt",true);
            writer.write(cat.getCatalog() + "\n");
            writer.flush();
            writer.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
    
    String getCatalog(){
        return this.Catalog_Name;
    }
}
