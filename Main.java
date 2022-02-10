import java.util.*;

public class Main{
    static Scanner keyin = new Scanner(System.in);
    public static void main(String[] args){
        //login()
        Login login = new Login();
        Function func = new Function();
        Config.readConfig();
        DataManage.readData();
        Catalog.readCatalog();
        login.LogIn();
        func.command();
    }
}