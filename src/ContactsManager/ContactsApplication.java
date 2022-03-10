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
        System.out.println("Please select an option below: \n" + "------------------------------------ \n" + "1. View Contacts\n" + "2. Add a new contact\n" + "3. Search for a contact by name\n" + "4. Delete an existing contact\n" + "5. Exit\n" + "------------------------------------\n");
        int userChoice = sc.nextInt();
        return userChoice;
    }

    public static void addContact(ArrayList<Contacts> contactsList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        String phoneNumber;
        do {
            System.out.println("Add a new contact to your list..." + "\nEnter first name: ");
            firstName = scanner.nextLine();
        } while (firstName.contains("|"));
        do {
            System.out.println("Enter last name: ");
            lastName = scanner.nextLine();
        } while (lastName.contains("|"));
        do {
            System.out.println("Enter the telephone number:  ");
            phoneNumber = scanner.nextLine();
        } while (phoneNumber.contains("|"));
//        Files.write(Paths.get("data", "contacts.txt"), Arrays.asList(firstName + " " + lastName + " | " + phoneNumber + " "), StandardOpenOption.APPEND);
//        ContactList.add(new Contacts(firstName, lastName, phoneNumber));
        contactsList.add(new Contacts(firstName, lastName, phoneNumber));
        System.out.println(firstName + " " + lastName + " was successfully added to your contacts list.");
    }

    public static void showContactList(ArrayList<Contacts> contactsList) {
        String leftAlignFormat = " %-20s | %-12s |%n";
        System.out.printf(leftAlignFormat, "Name", "Phone Number");
        System.out.println(" -------------------------------------");
        for (Contacts contact : contactsList) {
            System.out.printf(leftAlignFormat, contact.getFirstName() + " " + contact.getLastName(), contact.getPhoneNumber());
        }
//        Path contactsPath = Paths.get("data", "contacts.txt");
//        List<String> ContactList = null;
//        try {
//            ContactList = Files.readAllLines(contactsPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < ContactList.size(); i++) {
//            String[] arr = ContactList.get(i).split("\\|");
//            System.out.printf(leftAlignFormat, arr[0], arr[1]);
//        }
    }

    public static void searchContacts() {
        Scanner searcher = new Scanner(System.in);
        System.out.println("Enter first or last name:  ");
        String userSearch = searcher.nextLine();
        Path contactsPath = Paths.get("data", "contacts.txt");
        List<String> ContactList;
        try {
            ContactList = Files.readAllLines(contactsPath);
            for (String contact : ContactList) {
                if (contact.toLowerCase().contains(userSearch.toLowerCase())) {
                    System.out.println(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first or last name of the contact you would like to delete:  ");
        String nameToDelete = scan.nextLine();
        Path contactsPath = Paths.get("data", "contacts.txt");
        List<String> contactList;
        try {
            contactList = Files.readAllLines(contactsPath);
            List<String> updatedList = new ArrayList<>();
            for (String contact : contactList) {
                if (contact.toLowerCase().contains(nameToDelete.toLowerCase())) {
                    continue;
                }
                updatedList.add(contact);
            }
            for (String name : updatedList) {
                System.out.println(name);
            }
            Files.write(Paths.get("data", "contacts.txt"), updatedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        Path contactsPath = Paths.get("data", "contacts.txt");
        if (Files.notExists(contactsPath)) {
            Path contactsDirectory = Paths.get("data");
            Files.createDirectories(contactsDirectory);
            Files.createFile(contactsPath);
        }
        List<String> contactsStringList = Files.readAllLines(contactsPath);
        ArrayList<Contacts> contactsList = new ArrayList<>();
        for (String contactString : contactsStringList) {
            String[] contactInfo = contactString.split("\\|");
            String[] contactName = contactInfo[0].split(" ");
            contactsList.add(new Contacts(contactName[0], contactName[1], contactInfo[1]));
        }
        while (true) {
            int userChoice = mainMenu();
            switch (userChoice) {
                case 1:
                    showContactList(contactsList);
                    break;
                case 2:
                    addContact(contactsList);
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    deleteContact();
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

