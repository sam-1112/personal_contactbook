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
        DataManage.printTitle(true, true, true, true, true);
        DataManage.sortData(DataManage.people);
        for(People p : DataManage.people){
            p.print(true, true, true, true, true);
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
        for(int page = 0 ; page < ((DataManage.people.size()/data_per_page) + 1) && detail < DataManage.people.size() ; page++){
            for(int i = 0 ; i < data_per_page && detail < DataManage.people.size(); i++){
                data[page][i] = DataManage.people.get(detail++);
            }
        }

        for(int page = 0 ; (page % data_per_page != 0) ?page < (DataManage.people.size()/data_per_page) + 1 :page < (DataManage.people.size()/data_per_page); ){
            boolean flag = true;
            DataManage.printTitle(true, true, true, true, true);
            for(int i = 0 ; i < data_per_page && data[page][i] != null; i++){
                data[page][i].print(true, true, true, true, true);
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
                p.print(true, true, true, true, true);
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
                    p.print(true, true, true, true, true);
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
        boolean flag = true;
        People modify_person = new People();

        for(People p : DataManage.people){
            if(Integer.parseInt(command) == p.getID()){
                System.out.println("Search_result:");
                DataManage.printTitle(true, true, true, true, true);
                p.print(true, true, true, true, true);
                break;
            }
        }
        
        while(flag){
            System.out.println("New_name:");
            command = Main.keyin.nextLine();
            if(command.length() != 0 && command.length() <= 12 ){
                modify_person.setName(command);
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
            if(command.matches("[0-9]{4}-[0-9]{6}")){
                modify_person.setPhone(command);
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
            if(command.equalsIgnoreCase(Catalog.catalog.get(command.compareTo("a")).getCatalog())){
                modify_person.setCatalog(Catalog.catalog.get(command.compareTo("a")).getCatalog());
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
            if(command.matches("") ){
                modify_person.setName(command);
                flag = false;
            }
            else{
                System.out.println("Error_wrong_data");
                System.out.println("Please_input_again:");
            }
        }
    }
}
