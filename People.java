import java.util.StringTokenizer;

public class People {
    private int ID;
    private String Name;
    private String Phone;
    private String Catalog;
    private String Email;
    private String Birthday;

    public People(){
        this.ID = 0;
        this.Name = "";
        this.Phone = "";
        this.Catalog = "";
        this.Email = "";
        this.Birthday = "";
    }
    public People(int id, String name, String phone, String catalog, String email, String birthday){
        this.ID = id;
        this.Name = name;
        this.Phone = phone;
        this.Catalog = catalog;
        this.Email = email;
        this.Birthday = birthday;
    }

    public People(String line){
        StringTokenizer tokens = new StringTokenizer(line);
        this.ID = Integer.parseInt(tokens.nextToken());
        this.Name = tokens.nextToken();
        this.Phone = tokens.nextToken();
        this.Catalog = tokens.nextToken();
        this.Email = tokens.nextToken();
        this.Birthday = tokens.nextToken();
    }

    public String printfile(boolean name, boolean phone, boolean catalog, boolean email, boolean birthday){
        String ans =  String.format("%04d ", this.ID);
        if(name)
            ans += this.Name + " ";
        if(phone)
            ans += this.Phone + " ";
        if(catalog)
            ans += this.Catalog + " ";
        if(email)
            ans += this.Email + " ";
        if(birthday)
            ans += this.Birthday + " ";
        return ans.trim();
    }

    public String print(boolean name, boolean phone, boolean catalog, boolean email, boolean birthday){
        String person = ""; 
        person += String.format("%04d ", this.ID);
        if(name){
            person += String.format("%-12s ", this.Name);
        }
        if(phone){
            person += String.format("%-11s ", this.Phone);
        }
        if(catalog){
            person += String.format("%-12s ", this.Catalog);
        }
        if(email){
            person += String.format("%-24s ", this.Email);
        }
        if(birthday){
            person += String.format("%-4s", this.Birthday);
        }
        return person.trim();
    }
    public void print_data(boolean name, boolean phone, boolean catalog, boolean email, boolean birthday){
        String person = ""; 
        person += String.format("%04d", this.ID);
        if(name){
            person += String.format("%-12s", this.Name);
        }
        if(phone){
            person += String.format("%-11s", this.Phone);
        }
        if(catalog){
            person += String.format("%-12s", this.Catalog);
        }
        if(email){
            person += String.format("%-24s", this.Email);
        }
        if(birthday){
            person += String.format("%-4s", this.Birthday);
        }
        System.out.println(person.trim());
    }

    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.Name;
    }
    public String getPhone(){
        return this.Phone;
    }
    public String getCatalog(){
        return this.Catalog;
    }
    public String getEmail(){
        return this.Email;
    }
    public String getBirthday(){
        return this.Birthday;
    }
    public void setID(int id){
        this.ID = id;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setPhone(String phone){
        this.Phone = phone;
    }
    public void setCatalog(String catalog){
        this.Catalog = catalog;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public void setBirthday(String birth){
        this.Birthday = birth;
    }
}
