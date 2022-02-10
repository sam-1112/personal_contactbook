import java.io.*;
import java.util.*;
//import java.lang.*;

public class DataManage {
    static ArrayList<People> people = new ArrayList<People>();
    
    public static void readData(){
        try{
            Scanner read = new Scanner(new File("Data.txt"));
            for(;read.hasNextLine();){
                People data = new People(read.nextLine());
                people.add(data);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void printTitle(boolean name, boolean phone, boolean catalog, boolean email, boolean birthday){
        String title = String.format("%-4s", "[ID]");
        if(name){
            title += String.format("%-12s","[Name]");
        }
        if(phone){
            title += String.format("%-11s", "[Phone]");
        }
        if(catalog){
            title += String.format("%-12s", "[Catalog]");
        }
        if(email){
            title += String.format("%-24s", "[Email]");
        }
        if(birthday){
            title += String.format("%-4s", "[BD]");
        }
        System.out.println(title.trim());
    }

    public static void sortData(ArrayList<People> peoples){
        boolean asc = Config.config.get("show_sort_order").equalsIgnoreCase("asc");
        switch(Config.config.get("show_sort_property")){
            case "id" -> {
                if(asc){
                    Collections.sort(peoples, new IDSort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
            case "name" -> {
                if(asc){
                    Collections.sort(peoples, new NameSort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
            case "phone" -> {
                if(asc){
                    Collections.sort(peoples, new PhoneSort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
            case "catalog" -> {
                if(asc){
                    Collections.sort(peoples, new CatalogSort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
            case "email" -> {
                if(asc){
                    Collections.sort(peoples, new EmailSort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
            case "birthday" -> {
                if(asc){
                    Collections.sort(peoples, new BirthdaySort());
                }
                else{
                    Collections.reverse(peoples);
                }
            }
       }
    }
}
class IDSort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getID() - p2.getID();
    }
}
class NameSort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getName().compareTo(p2.getName());
    }
}
class PhoneSort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getPhone().compareTo(p2.getPhone());
    }
}
class CatalogSort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getCatalog().compareTo(p2.getCatalog());
    }
}
class EmailSort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getEmail().compareTo(p2.getEmail());
    }
}
class BirthdaySort implements Comparator<People>{
    public int compare(People p1, People p2){
        return p1.getBirthday().compareTo(p2.getBirthday());
    }
}