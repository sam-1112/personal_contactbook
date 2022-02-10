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
    String getCatalog(){
        return this.Catalog_Name;
    }
}
