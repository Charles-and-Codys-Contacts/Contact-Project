package ContactsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ContactsApplication {
    private static List<Contacts> ContactList = new ArrayList<>();

    public static int mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to C & C Contacts Factory\n\n");
        System.out.println("Please select an option below: \n" +
                "------------------------------------ \n" +
                "1. View Contacts\n" +
                "2. Add a new contact\n" +
                "3. Search for a contact by name\n" +
                "4. Delete an existing contact\n" +
                "5. Exit\n" +
                "------------------------------------\n");

        int userChoice = sc.nextInt();
        return userChoice;
    }

        public static void addContact() throws IOException{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Add a new contact to your list..."+
                    "\nEnter the first name:  ");
            String firstName = scanner.nextLine();
            System.out.println("Enter the last name:  ");
            String lastName = scanner.nextLine();
            System.out.println("Enter the telephone number:  ");
            String phoneNumber = scanner.nextLine();

            Files.write(Paths.get("data", "contacts.txt"), Arrays.asList(firstName + " " + lastName + "|" + phoneNumber), StandardOpenOption.APPEND);

            ContactList.add(new Contacts(firstName, lastName, phoneNumber));
            System.out.println(firstName + " " + lastName + " was successfully added to your contacts list.");
        }

        public static void showContactList(){
            System.out.println("Name        | Phone Number    |");
            Path contactsPath = Paths.get("data", "contacts.txt");
            List<String> ContactList = null;
            try{
                ContactList = Files.readAllLines(contactsPath);
            }catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < ContactList.size(); i++){
                System.out.println(ContactList.get(i));
            }
        }

        public static void searchContacts(){
            Scanner searcher = new Scanner(System.in);
            System.out.println("Enter first or last name:  ");
            String userSearch = searcher.nextLine();
            Path contactsPath = Paths.get("data", "contacts.txt");
            List<String> ContactList;
            try{
                ContactList = Files.readAllLines(contactsPath);
                for (String contact : ContactList){
                    if (contact.toLowerCase().contains(userSearch.toLowerCase())){
                        System.out.println(contact);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        public static void deleteContact(){

        }


    public static void main(String[] args) throws IOException{

        while (true){
            int userChoice = mainMenu();
            switch(userChoice){
                case 1:
                    showContactList();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Thank you for using C & C Contacts Factory!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a choice 1 - 5, dummy");
                    break;
            }
        }
    }

    }

