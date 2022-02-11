import java.util.*;
//import java.io.*;

public class Function {
    //DataManage dataManager = new DataManage();
    void mainMenu(){
        System.out.println("****************************************");
        System.out.println("[1].Show_all [2].Show_per_page [3].Show_by_catalog");
        System.out.println("[4].Search [5].Modify [6].Delete [7].Add_contact");
        System.out.println("[8].Add_catalog [9].Show_all_catalog [10].Set_display_field");
        System.out.println("[11].Set_show_perpage [12].Set_Order [13].Set_sort_by_field");
        System.out.println("[14].Show_raw_data [15].Data_optimize [99].Exit_system");
        System.out.println("****************************************");
    }

    void command(){
        String command;
        for(;;){
            boolean flag = false;
            mainMenu();
            command = Main.keyin.nextLine();
            switch(command){
                case "1" -> {
                    show_all();
                    flag = true;
                }
                case "2" -> show_per_page();
                case "3" -> {
                    show_by_catalog();
                    flag = true;
                }
                case "4" -> search();
                case "5" -> {
                    modify();
                    flag = true;
                }
                case "6" -> {
                    delete();
                    flag = true;
                }
                case "7" -> {
                    add_contact();
                    flag = true;
                }
                case "8" -> {
                    add_catalog();
                    flag = true;
                }
                case "9" -> {
                    show_catalog();
                    flag = true;
                }
                case "10" -> {
                    set_display_field();
                    flag = true;
                }
                case "11" -> {
                    set_show_perpage();
                    flag = true;
                }
                case "12" -> {
                    set_order();
                    flag = true;
                }
                case "13" -> {
                    set_sort_by_field();
                    flag = true;
                }
                case "14" -> {
                    show_raw_data();
                    flag = true;
                }
                case "15" -> {
                    data_optimize();
                    flag = true;
                }
                case "99" -> System.exit(0);
                default -> {
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again");
                    continue;
                }
            }
            if(flag){
                System.out.println("[0].Go_back_to_main_menu[99].Exit_system");
                while(true){
                    command = Main.keyin.nextLine();
                    if(command.equals("0")){
                        break;
                    }
                    else if(command.equals("99")){
                        System.exit(0);
                    }
                    else{
                        System.out.println("Error_wrong_command");
                        System.out.println("Please_enter_again");
                    }
                }   
            }
        }
    }

    void show_all(){
        DataManage.printTitle(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
        DataManage.sortData(DataManage.people);
        for(People p : DataManage.people){
            p.print_data(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
        }
    }

    void show_per_page(){
        DataManage.sortData(DataManage.people);
        String command = "";
        System.out.println("Choose_show_per_page:");
        System.out.println("[3].3_data_per_page [5].5_data_per_page [10].10_data_per_page");
        System.out.println("[d].default [0].Go_back_to_main_menu [99].Exit_system");
        command = Main.keyin.nextLine();
        switch(command){
            case "3" -> {
                show_page_data(Integer.parseInt(command));
            }
            case "5" -> {
                show_page_data(Integer.parseInt(command));
            }
            case "10" -> {
                show_page_data(Integer.parseInt(command));
            }
            case "d" -> {
                show_page_data(Integer.parseInt(Config.config.get("show_defalt_perpage")));
            }
            case "0" -> {
                return;
            }
            case "99" -> {
                System.exit(0);
            }
        }
    }

    void show_page_data(int data_per_page){
        int detail = 0;
        String command = "";
        People[][] data = new People[(DataManage.people.size()/data_per_page) + 1][data_per_page];
        for(int page_in = 0 ; page_in < ((DataManage.people.size()/data_per_page) + 1) && detail < DataManage.people.size() ; page_in++){
            for(int i = 0 ; i < data_per_page && detail < DataManage.people.size(); i++){
                data[page_in][i] = DataManage.people.get(detail++);
            }
        }
        
        for(int page = 0 ; page < data.length ; ){
            boolean flag = true;
            DataManage.printTitle(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                    DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
            for(int i = 0 ; i < data_per_page && data[page][i] != null; i++){
                data[page][i].print_data(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                            DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
            }
            
            if(page == 0){
                System.out.println("[2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                while(flag){
                    command = Main.keyin.nextLine();
                    switch(command){
                        case "2":
                            page++;flag = false;break;
                        case "0":
                            return;
                        case "99":
                            System.exit(0);
                        default:
                            System.out.println("Error_wrong_command");
                            System.out.println("Please_enter_again");
                    }
                }
            }
            else if((DataManage.people.size() % data_per_page != 0) ?page < (DataManage.people.size()/data_per_page) :page < ((DataManage.people.size()/data_per_page) - 1)){
                System.out.println("[1].Last_page [2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                while(flag){
                    command = Main.keyin.nextLine();
                    switch(command){
                        case "1":
                            page--;flag = false;break;
                        case "2":
                            page++;flag = false;break;
                        case "0":
                            return;
                        case "99":
                            System.exit(0);
                        default:
                            System.out.println("Error_wrong_command");
                            System.out.println("Please_enter_again");
                    }
                }
            }
            else{
                System.out.println("[1].Last_page [0].Go_back_to_main_menu [99].Exit_system");
                while(flag){
                    command = Main.keyin.nextLine();
                    switch(command){
                        case "1":
                            page--;flag = false;break;
                        case "0":
                            return;
                        case "99":
                            System.exit(0);
                        default:
                            System.out.println("Error_wrong_command");
                            System.out.println("Please_enter_again");
                    }
                }
            }
        }
    }

    void show_by_catalog(){
        String command = "";
        System.out.println("Catalogs:");
        int i;
        for(i = 0 ; i < Catalog.catalog.size() ; ++i){
            if(i != 0){System.out.print(" ");}
            System.out.print("[" + (char)(i+97) + "]." + Catalog.catalog.get(i).getCatalog());
        }
        System.out.println("\n[0].Go_back_to_main_menu [99].Exit_system");
        System.out.println("Input_catalog_to_show:");
        while(true){
            command = Main.keyin.nextLine();
            if(command.compareTo("a") >= 0 && command.compareTo("a") < i){
                break;
            }
            else if(command.equals("0"))
                return;
            else if(command.equals("99")){
                System.exit(0);
            }
            else{
                System.out.println("Error_wrong_command");
                System.out.println("Please_enter_again");
            }
        }
        
        for(People p : DataManage.people){
            if(p.getCatalog().equalsIgnoreCase(Catalog.catalog.get(command.compareTo("a")).getCatalog())){
                p.print_data(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                        DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
            }
        }
    }

    void search(){
        String command = "", target = "";
        ArrayList<People> person = new ArrayList<>();

        for(;;){
            boolean flag = false;
            System.out.println("Search by:");
            System.out.println("[a].ID [b].Name [c].Birthday");
            System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
            for(;;){
                person.clear();
                command = Main.keyin.nextLine();
                if(command.equals("a")){
                    System.out.println("Input_target:");
                    target = Main.keyin.nextLine();
                    for(People p : DataManage.people){
                        if(p.getID() == Integer.parseInt(target)){
                            //p.print(true, true, true, true, true);
                            person.add(p);
                            flag = true;
                        }
                    }
                    if(!flag){
                        System.out.println("Error_no_result");
                    }
                    break;
                }
                else if(command.equals("b")){
                    System.out.println("Input_target:");
                    target = Main.keyin.nextLine();
                    for(People p : DataManage.people){
                        if(p.getName().equals(target)){
                            //p.print(true, true, true, true, true);
                            person.add(p);
                            flag = true;
                        }
                    }
                    if(!flag){
                        System.out.println("Error_no_result");
                    }
                    break;
                }
                else if(command.equals("c")){
                    System.out.println("Input_target:");
                    target = Main.keyin.nextLine();
                    for(People p : DataManage.people){
                        if(p.getBirthday().equals(target)){
                            //p.print(true, true, true, true, true);
                            person.add(p);
                            flag = true;
                        }
                    }
                    if(!flag){
                        System.out.println("Error_no_result");
                    }
                    break;
                }
                else if(command.equals("0")){
                    return;
                }
                else if(command.equals("99")){
                    System.exit(0);
                }
                else{
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again:");
                    continue;
                }
            }
            if(person.size() != 0){
                for(People p : person){
                    System.out.println(p.print(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                            DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday"))));
                }
            }
            System.out.println("[1].Restart_search [0].Go_back_to_main_menu [99].Exit_system");
            for(;;){
                command = Main.keyin.nextLine();
                if(command.equals("1")){
                    break;
                }
                else if(command.equals("0")){
                    return;
                }
                else if(command.equals("99")){
                    System.exit(0);
                }
                else{
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again:");
                    continue;
                }
            }
        }
    }

    void modify(){
        System.out.println("Input_ID_to_be_modified:");
        String command = Main.keyin.nextLine();
        boolean flag = true, search_flag = false;

        for(People p : DataManage.people){
            if(Integer.parseInt(command) == p.getID()){
                search_flag = true;
                System.out.println("Search_result:");
                DataManage.printTitle(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                        DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
                p.print_data(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                        DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
                while(flag){
                    System.out.println("New_name:");
                    command = Main.keyin.nextLine();
                    if(command.length() == 0){
                        break;
                    }
                    if(DataManage.checkName(command)){
                        p.setName(command);
                        flag = false;
                    }
                    else{
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                }
                flag = true;
                while(flag){
                    System.out.println("New_phone:");
                    command = Main.keyin.nextLine();
                    if(DataManage.checkPhone(command)){
                        p.setPhone(command);
                        flag = false;
                    }
                    else if(command.length() == 0){
                        flag = false;
                    }
                    else{
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                }
                flag = true;
                while(flag){
                    System.out.print("Catalogs:");
                    int i;
                    for(i = 0 ; i < Catalog.catalog.size() ; ++i){
                        if(i != 0){System.out.print(" ");}
                        System.out.print("[" + (char)(i+97) + "]." + Catalog.catalog.get(i).getCatalog());
                    }
                    System.out.println("New_catalog:");
                    command = Main.keyin.nextLine();
                    if(command.length() == 0){
                        break;
                    }
                    if(DataManage.checkCatalog(command)){
                        p.setCatalog(Catalog.catalog.get(command.compareTo("a")).getCatalog());
                        flag = false;
                    }
                    else{
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                }
                flag = true;
                while(flag){
                    System.out.println("New_email:");
                    command = Main.keyin.nextLine();
                    if(DataManage.checkEmail(command)){
                        p.setEmail(command);
                        flag = false;
                    }
                    else if(command.length() == 0){
                        flag = false;
                    }
                    else{
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                }
                flag = true;
                while(flag){
                    System.out.println("New_birthday:");
                    command = Main.keyin.nextLine();
                    if(DataManage.checkBirthday(command)){
                        p.setBirthday(command);
                        flag = false;
                    }
                    else if(command.length() == 0 ){
                        flag = false;
                    }
                    else{
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                }
                break;
            }
        }
        if(!search_flag){
            System.out.println("Doesn't exist");
            return;
        }
        if(DataManage.writeData(DataManage.people)){
            System.out.println("Modify_data_success");
            DataManage.people.clear();
            DataManage.readData();
            return;
        }
    }

    void delete(){
        boolean search_flag = false;
        System.out.println("Input_ID_to_be_deleted:");
        String command = Main.keyin.nextLine();
        for(People p : DataManage.people){
            if(Integer.parseInt(command) == p.getID()){
                DataManage.people.remove(p);
                search_flag = true;
                break;
            }
        }
        if(!search_flag){
            System.out.println("Doesn't exist");
            return;
        }
        if(DataManage.writeData(DataManage.people)){
            System.out.println("Delete_data_success");
            DataManage.people.clear();
            DataManage.readData();
            return;
        }
    }

    void add_contact(){
        People person = new People();
        String command = "";
        while(true){
            System.out.println("Name:");
            command = Main.keyin.nextLine();
            if(DataManage.checkName(command)){
                person.setName(command);
                break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        while(true){
            System.out.println("Phone:");
            command = Main.keyin.nextLine();
            if(DataManage.checkPhone(command)){
                person.setPhone(command);
                break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        while(true){
            System.out.print("Catalogs:");
            for(int i = 0 ; i < Catalog.catalog.size() ; ++i){
                if(i != 0){System.out.print(" ");}
                System.out.print("[" + (char)(i+97) + "]." + Catalog.catalog.get(i).getCatalog());
            }
            System.out.println("\nCatalog:");
            command = Main.keyin.nextLine();
            if(DataManage.checkCatalog(command)){
                person.setCatalog(Catalog.catalog.get(command.compareTo("a")).getCatalog());
                break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        while(true){
            System.out.println("Email:");
            command = Main.keyin.nextLine();
            if(DataManage.checkEmail(command)){
                person.setEmail(command);
                break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        while(true){
            System.out.println("Birthday:");
            command = Main.keyin.nextLine();
            if(DataManage.checkBirthday(command)){
                person.setBirthday(command);
                break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        person.setID(DataManage.people.size()+1);
        //ArrayList<People> temp_people = new ArrayList<>();
        //temp_people.equals(DataManage.people);
        //DataManage.people.add(person);
        //DataManage.people.clear();
        DataManage.appendData(person);
        DataManage.people.clear();
        DataManage.readData();
        System.out.println("Add_contact_success");
    }

    void add_catalog(){
        System.out.println("Please_input_new_catalog:");
        String command = "";
        while(true){
            command = Main.keyin.nextLine();
            if(command.length() <= 12){
                Catalog cat = new Catalog(command);
                Catalog.catalog.add(cat);
                if(Catalog.writeCatalog(cat)){
                    System.out.println("Add_catalog_Gamer_success");
                }
                return;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
    }

    void show_catalog(){
        System.out.println("[Catalog]");
        for(Catalog c : Catalog.catalog){
            System.out.println(c.getCatalog());
        }
    }

    void set_display_field(){
        System.out.print("[1].Show_name:");
        if(Config.config.get("show_name").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [2].Show_phone:");
        if(Config.config.get("show_phone").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [3].Show_cat:");
        if(Config.config.get("show_catalog").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [4].Show_email:");
        if(Config.config.get("show_email").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [5].Show_bd:");
        if(Config.config.get("show_birthday").equalsIgnoreCase("true")){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
        System.out.println();

        String command = "";
        System.out.println("New_show_name(0/1):");
        while(true){
            command = Main.keyin.nextLine();
            if(command.equals("1")){
                Config.config.put("show_name", "true");break;
            }
            else if(command.equals("0")){
                Config.config.put("show_name", "false");break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        System.out.println("New_show_phone(0/1):");
        while(true){
            command = Main.keyin.nextLine();
            if(command.equals("1")){
                Config.config.put("show_phone", "true");break;
            }
            else if(command.equals("0")){
                Config.config.put("show_phone", "false");break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        System.out.println("New_show_catalog(0/1):");
        while(true){
            command = Main.keyin.nextLine();
            if(command.equals("1")){
                Config.config.put("show_catalog", "true");break;
            }
            else if(command.equals("0")){
                Config.config.put("show_catalog", "false");break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        System.out.println("New_show_email(0/1):");
        while(true){
            command = Main.keyin.nextLine();
            if(command.equals("1")){
                Config.config.put("show_email", "true");break;
            }
            else if(command.equals("0")){
                Config.config.put("show_email", "false");break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
        System.out.println("New_show_birthday(0/1):");
        while(true){
            command = Main.keyin.nextLine();
            if(command.equals("1")){
                Config.config.put("show_birthday", "true");break;
            }
            else if(command.equals("0")){
                Config.config.put("show_birthday", "false");break;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }

        System.out.print("[1].Show_name:");
        if(Config.config.get("show_name").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [2].Show_phone:");
        if(Config.config.get("show_phone").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [3].Show_cat:");
        if(Config.config.get("show_catalog").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [4].Show_email:");
        if(Config.config.get("show_email").equalsIgnoreCase("true")){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
        System.out.print(" [5].Show_bd:");
        if(Config.config.get("show_birthday").equalsIgnoreCase("true")){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

        Config.writeConfig(Config.config);
    }

    void set_show_perpage(){
        System.out.println("show_defalt_perpage:" + Config.config.get("show_defalt_perpage"));
        System.out.println("new_show_defalt_perpage:");
        String command = "";
        while(true){
            command = Main.keyin.nextLine();
            if(Integer.parseInt(command) > 0 && Integer.parseInt(command) <= DataManage.people.size()){
                Config.config.put("show_defalt_perpage", command);
                Config.writeConfig(Config.config);
                Config.config.clear();
                Config.readConfig();
                System.out.println("show_defalt_perpage:" + Config.config.get("show_defalt_perpage"));
                return;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
    }

    void set_order(){
        System.out.println("show_sort_order:" + Config.config.get("show_sort_order"));
        System.out.println("Please_input_new_sort_order:");
        String command = "";
        while(true){
            command = Main.keyin.nextLine();
            if(command.equalsIgnoreCase("asc")){
                Config.config.put("show_sort_order", command);
                Config.writeConfig(Config.config);
                Config.config.clear();
                Config.readConfig();
                System.out.println("show_sort_order:" + Config.config.get("show_sort_order"));
                return;
            }
            else if(command.equalsIgnoreCase("des")){
                Config.config.put("show_sort_order", command);
                Config.writeConfig(Config.config);
                Config.config.clear();
                Config.readConfig();
                System.out.println("show_sort_order:" + Config.config.get("show_sort_order"));
                return;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
    }

    void set_sort_by_field(){
        String command = "";
        boolean flag = true;
        System.out.println("[1].ID [2].Name [3].Phone [4].Cat [5].Email [6].Bd");
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        while(flag){
            command = Main.keyin.nextLine();
            switch(command){
                case "1" -> {
                    Config.config.put("show_sort_property", "id");
                    System.out.println("Sorted_by:ID");
                    flag = false;
                    break;
                }
                case "2" -> {
                    Config.config.put("show_sort_property", "name");
                    System.out.println("Sorted_by:Name");
                    flag = false;
                    break;
                }
                case "3" -> {
                    Config.config.put("show_sort_property", "phone");
                    System.out.println("Sorted_by:Phone");
                    flag = false;
                    break;
                }
                case "4" -> {
                    Config.config.put("show_sort_property", "catalog");
                    System.out.println("Sorted_by:Catalog");
                    flag = false;
                    break;
                }
                case "5" -> {
                    Config.config.put("show_sort_property", "email");
                    System.out.println("Sorted_by:Email");
                    flag = false;
                    break;
                }
                case "6" -> {
                    Config.config.put("show_sort_property", "birthday");
                    System.out.println("Sorted_by:Birthday");
                    flag = false;
                    break;
                }
                case "0" -> {
                    return;
                }
                case "99" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_input_again:");
                }
            }
        }
        Config.writeConfig(Config.config);
        Config.config.clear();
        Config.readConfig();
    }

    void show_raw_data(){
        DataManage.printTitle(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
        for(People p : DataManage.people){
            p.print_data(DataManage.getValue(Config.config.get("show_name")), DataManage.getValue(Config.config.get("show_phone")), 
                DataManage.getValue(Config.config.get("show_catalog")), DataManage.getValue(Config.config.get("show_email")), DataManage.getValue(Config.config.get("show_birthday")));
        }
    }

    void data_optimize(){
        System.out.println("Please_confirm_data_optimize_y_or_n:");
        String command = "";
        while(true){
            command = Main.keyin.nextLine();
            if(command.equalsIgnoreCase("y")){
                DataManage.writeData(DataManage.people);
                System.out.println("Data_optimize_success");
                return;
            }
            else if(command.equalsIgnoreCase("n")){
                System.out.println("Data_optimize_denied");
                return;
            }
            else{
                System.out.println("Error_wrong_command");
                System.out.println("Please_input_again:");
            }
        }
    }
}
