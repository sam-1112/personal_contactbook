import java.util.*;
import java.io.*;


public class Login {
    
    void LogIn(){
        String acc, pw;
        int error_count = 0;
        while(error_count < 3){
            System.out.println("Account:");
            acc = Main.keyin.nextLine();
            System.out.println("Password:");
            pw = Main.keyin.nextLine();
            System.out.println("Input_Verify_String:");
            String verify = Main.keyin.nextLine();
            if(readAccount(acc, pw) && Config.config.get("verify_string").equals(verify)){
                System.out.println("Login_success");
                return;
            }
            System.out.println("Error_wrong_account_password_or_verify_string");
            error_count++;
        }
        System.exit(0);
    }

    public boolean readAccount(String acc, String pw){
        try{
            Scanner read = new Scanner(new File("accout.txt"));
            StringTokenizer tokens = new StringTokenizer(read.nextLine()," ");
            if(acc.equals(tokens.nextToken()) && pw.equals(tokens.nextToken())){
                return true;
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
