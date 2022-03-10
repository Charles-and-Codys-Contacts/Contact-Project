package ContactsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ContactsApplication {
    public static int mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWelcome to C & C Contacts Factory\n------------------------------------ \n1. View Contacts\n2. Add a new contact\n3. Search for a contact by name\n4. Delete an existing contact\n5. Save contacts\n6. Exit\n------------------------------------\nPlease select an option: ");
        return sc.nextInt();
    }

    public static void addContact(ArrayList<Contact> contactsList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        String phoneNumber;
        do {
            System.out.print("Add a new contact to your list...\nEnter first name: ");
            firstName = scanner.nextLine();
            if (firstName.contains("|")) {
                System.out.println("Names cannot have \"|\"s. Try again.");
            } else {
                break;
            }
        } while (true);
        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (lastName.contains("|")) {
                System.out.println("Names cannot have \"|\"s. Try again.");
            } else {
                break;
            }
        } while (true);
        do {
            System.out.print("Enter the telephone number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.length() != 12) {
                System.out.println("Phone numbers must have 10 digits. Format: [###-###-####]");
            } else if (phoneNumber.contains("|")) {
                System.out.println("Phone numbers cannot have \"|\"s. Try again.");
            } else {
                break;
            }
        } while (true);
        contactsList.add(new Contact(firstName, lastName, phoneNumber));
        contactsList.sort(new ContactComparator());
        System.out.println(firstName + " " + lastName + " was successfully added to your contacts list.");
    }

    public static void showContactList(ArrayList<Contact> contactsList) {
        String leftAlignFormat = " %-20s | %-12s |%n";
        System.out.printf(leftAlignFormat, "Name", "Phone Number");
        System.out.println(" -------------------------------------");
        for (Contact contact : contactsList) {
            System.out.printf(leftAlignFormat, contact.getFirstName() + " " + contact.getLastName(), contact.getPhoneNumber());
        }
    }

    public static void searchContacts(ArrayList<Contact> contactsList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name or phone number: ");
        String userSearch = scanner.nextLine().toLowerCase();
        ArrayList<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().contains(userSearch) || contact.getLastName().toLowerCase().contains(userSearch) || contact.getPhoneNumber().contains(userSearch)) {
                matchedContacts.add(contact);
            }
        }
        for (Contact contact : matchedContacts) {
            System.out.printf("%s %s - %s\n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());
        }
    }

    public static void deleteContact(ArrayList<Contact> contactsList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first or last name: ");
        String userSearch = scanner.nextLine().toLowerCase();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().contains(userSearch) || contact.getLastName().toLowerCase().contains(userSearch)) {
                System.out.printf("Delete %s %s? [y/N] ", contact.getFirstName(), contact.getLastName());
                if (scanner.nextLine().toLowerCase().startsWith("y")) {
                    System.out.printf("%s %s deleted.", contact.getFirstName(), contact.getLastName());
                    contactsList.remove(contact);
                    break;
                }
            }
        }
    }

    public static void saveContacts(Path contactsPath, ArrayList<Contact> contactsList) {
        List<String> contactsStringList = new ArrayList<>();
        for (Contact contact : contactsList) {
            contactsStringList.add(contact.getFirstName() + " " + contact.getLastName() + "|" + contact.getPhoneNumber());
        }
        try {
            Files.write(contactsPath, contactsStringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path contactsPath = Paths.get("data", "contacts.txt");
        if (Files.notExists(contactsPath)) {
            Path contactsDirectory = Paths.get("data");
            Files.createDirectories(contactsDirectory);
            Files.createFile(contactsPath);
        }
        List<String> contactsStringList = Files.readAllLines(contactsPath);
        ArrayList<Contact> contactsList = new ArrayList<>();
        for (String contactString : contactsStringList) {
            String[] contactInfo = contactString.split("\\|");
            String[] contactName = contactInfo[0].split(" ");
            contactsList.add(new Contact(contactName[0], contactName[1], contactInfo[1]));
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
                    searchContacts(contactsList);
                    break;
                case 4:
                    deleteContact(contactsList);
                    break;
                case 5:
                    saveContacts(contactsPath, contactsList);
                    break;
                case 6:
                    System.out.print("Save contacts? [y/N] ");
                    if (scanner.nextLine().toLowerCase().startsWith("y")) {
                        saveContacts(contactsPath, contactsList);
                        System.out.println("Contacts saved!");
                    }
                    System.out.println("Thank you for using C & C Contacts Factory!");
                    System.exit(0);
                default:
                    System.out.println("Enter a choice 1 - 5, dummy");
                    break;
            }
        }
    }
}