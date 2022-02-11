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

    public static boolean writeData(ArrayList<People> peoples){
        try{
            FileWriter writer = new FileWriter("Data.txt");
            for(People p : DataManage.people){
                writer.write(p.printfile(true, true, true, true, true) + "\n");
            }
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean appendData(People person){
        try{
            FileWriter writer = new FileWriter("Data.txt",true);
            writer.write(person.printfile(true, true, true, true, true) + "\n");
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
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
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
            case "name" -> {
                if(asc){
                    Collections.sort(peoples, new NameSort());
                }
                else{
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
            case "phone" -> {
                if(asc){
                    Collections.sort(peoples, new PhoneSort());
                }
                else{
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
            case "catalog" -> {
                if(asc){
                    Collections.sort(peoples, new CatalogSort());
                }
                else{
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
            case "email" -> {
                if(asc){
                    Collections.sort(peoples, new EmailSort());
                }
                else{
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
            case "birthday" -> {
                if(asc){
                    Collections.sort(peoples, new BirthdaySort());
                }
                else{
                    Collections.sort(peoples, new IDSort());
                    Collections.reverse(peoples);
                }
            }
       }
    }

    public static boolean checkName(String name){
        if(name.matches("[a-zA-z]*") && name.length() <= 12){
            return true;
        }
        return false;
    }
    public static boolean checkPhone(String phone){
        if(phone.matches("[0-9]{4}-[0-9]{6}")){
            return true;
        }
        return false;
    }
    public static boolean checkCatalog(String catalog){
        if(catalog.compareTo("a") < Catalog.catalog.size() && catalog.compareTo("a") >= 0){
            return true;
        }
        return false;
    }
    public static boolean checkEmail(String email){
        if(email.matches("[A-Za-z0-9+_.-]+@(.+)$") && email.length() <= 24){
            return true;
        }
        return false;
    }
    public static boolean checkBirthday(String birth){
        if(birth.matches("[0-9]{4}")){
            return true;
        }
        return false;
    }

    public static boolean getValue(String s){
        if(s.equalsIgnoreCase("true")){
            return true;
        }
        return false;
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