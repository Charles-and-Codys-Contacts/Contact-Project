package ContactsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactsApplication {
    public static int mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWelcome to C & C Contacts Factory\n------------------------------------ \n1. View Contacts\n2. Add a new contact\n3. Search for a contact by name\n4. Edit a contact\n5. Delete an existing contact\n6. Save contacts\n7. Exit\n------------------------------------\nPlease select an option: ");
        return sc.nextInt();
    }

    public static boolean invalidName(String name) {
        if (name.length() == 0) {
            return true;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != '-') {
                return true;
            }
        }
        return false;
    }

    public static boolean invalidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 0) {
            return true;
        }
        if (!Character.isDigit(phoneNumber.charAt(0)) && phoneNumber.charAt(0) != '+' || phoneNumber.charAt(phoneNumber.length() - 1) == '-') {
            return true;
        }
        for (int i = 1; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i)) && phoneNumber.charAt(i) != '-') {
                return true;
            }
        }
        return false;
    }

    public static void addContact(ArrayList<Contact> contactsList) {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        String phoneNumber;
        System.out.println("Add a new contact to your list...");
        do {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (invalidName(firstName)) {
                System.out.println("Names cannot contain numbers or special characters. Try again.");
            } else {
                break;
            }
        } while (true);
        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (invalidName(lastName)) {
                System.out.println("Names cannot contain special characters or numbers. Try again.");
            } else {
                break;
            }
        } while (true);
        for (Contact contact : contactsList) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                System.out.print("This name already exists in your contact list. Continue? [y/N] ");
                if (scanner.nextLine().toLowerCase().startsWith("y")) {
                    break;
                } else {
                    System.out.println("Cancelling...");
                    return;
                }
            }
        }
        do {
            System.out.print("Enter telephone number: ");
            phoneNumber = scanner.nextLine();
            if (invalidPhoneNumber(phoneNumber)) {
                System.out.println("Phone numbers cannot contain letters or special characters. Try again.");
            } else {
                if (phoneNumber.length() == 10) {
                    String part1 = phoneNumber.substring(0, 3);
                    String part2 = phoneNumber.substring(3, 6);
                    String part3 = phoneNumber.substring(6);
                    phoneNumber = part1 + "-" + part2 + "-" + part3;
                } else if (phoneNumber.length() == 7) {
                    String part1 = phoneNumber.substring(0, 3);
                    String part2 = phoneNumber.substring(3);
                    phoneNumber = part1 + "-" + part2;
                }
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
        String userSearch;
        while (true) {
            System.out.print("Enter name or phone number: ");
            userSearch = scanner.nextLine().toLowerCase();
            if (userSearch.length() == 0) {
                System.out.println("You didn't enter anything. Try again.");
            } else {
                break;
            }
        }
        ArrayList<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().contains(userSearch) || contact.getLastName().toLowerCase().contains(userSearch) || contact.getPhoneNumber().contains(userSearch)) {
                matchedContacts.add(contact);
            }
        }
        if (matchedContacts.size() == 0) {
            System.out.println("No results.");
            return;
        }
        for (Contact contact : matchedContacts) {
            System.out.printf("%s %s - %s\n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());
        }
    }

    public static void editContact(ArrayList<Contact> contactsList) {
        Scanner scanner = new Scanner(System.in);
        String userSearch;
        while (true) {
            System.out.print("Enter first or last name: ");
            userSearch = scanner.nextLine().toLowerCase();
            if (userSearch.length() == 0) {
                System.out.println("You didn't enter anything. Try again.");
            } else {
                break;
            }
        }
        ArrayList<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : contactsList) {
            if (contact.getFirstName().toLowerCase().contains(userSearch) || contact.getLastName().toLowerCase().contains(userSearch)) {
                matchedContacts.add(contact);
            }
        }
        if (matchedContacts.size() == 0) {
            System.out.println("No results.");
            return;
        }
        while (true) {
            System.out.println("0. Cancel");
            for (int i = 0; i < matchedContacts.size(); i++) {
                System.out.printf("%d. %s %s - %s\n", i + 1, matchedContacts.get(i).getFirstName(), matchedContacts.get(i).getLastName(), matchedContacts.get(i).getPhoneNumber());
            }
            System.out.print("Who would you like to edit? ");
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput == 0) {
                    return;
                } else if (userInput <= matchedContacts.size()) {
                    System.out.printf("Editing %s %s\n", matchedContacts.get(userInput - 1).getFirstName(), matchedContacts.get(userInput - 1).getLastName());
                    String firstName;
                    String lastName;
                    String phoneNumber;
                    do {
                        System.out.print("Enter first name: ");
                        firstName = scanner.nextLine();
                        if (invalidName(firstName)) {
                            System.out.println("Names cannot contain numbers or special characters. Try again.");
                        } else {
                            break;
                        }
                    } while (true);
                    do {
                        System.out.print("Enter last name: ");
                        lastName = scanner.nextLine();
                        if (invalidName(lastName)) {
                            System.out.println("Names cannot contain special characters or numbers. Try again.");
                        } else {
                            break;
                        }
                    } while (true);
                    for (Contact contact : contactsList) {
                        if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                            System.out.print("This name already exists in your contact list. Continue? [y/N] ");
                            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                                break;
                            } else {
                                System.out.println("Cancelling...");
                                return;
                            }
                        }
                    }
                    do {
                        System.out.print("Enter telephone number: ");
                        phoneNumber = scanner.nextLine();
                        if (invalidPhoneNumber(phoneNumber)) {
                            System.out.println("Phone numbers cannot contain letters or special characters. Try again.");
                        } else {
                            if (phoneNumber.length() == 10) {
                                String part1 = phoneNumber.substring(0, 3);
                                String part2 = phoneNumber.substring(3, 6);
                                String part3 = phoneNumber.substring(6);
                                phoneNumber = part1 + "-" + part2 + "-" + part3;
                            } else if (phoneNumber.length() == 7) {
                                String part1 = phoneNumber.substring(0, 3);
                                String part2 = phoneNumber.substring(3);
                                phoneNumber = part1 + "-" + part2;
                            }
                            break;
                        }
                    } while (true);
                    for (Contact contact : contactsList) {
                        if (contact.getFirstName().equals(matchedContacts.get(userInput - 1).getFirstName()) && contact.getLastName().equals(matchedContacts.get(userInput - 1).getLastName()) && contact.getPhoneNumber().equals(matchedContacts.get(userInput - 1).getPhoneNumber())) {
                            System.out.printf("%s %s (%s) updated to %s %s (%s)\n", contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), firstName, lastName, phoneNumber);
                            contact.setFirstName(firstName);
                            contact.setLastName(lastName);
                            contact.setPhoneNumber(phoneNumber);
                            contactsList.sort(new ContactComparator());
                            return;
                        }
                    }
                } else {
                    System.out.println("Invalid input.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void deleteContact(ArrayList<Contact> contactsList) {
        Scanner scanner = new Scanner(System.in);
        String userSearch;
        while (true) {
            System.out.print("Enter first or last name: ");
            userSearch = scanner.nextLine().toLowerCase();
            if (userSearch.length() == 0) {
                System.out.println("You didn't enter anything. Try again.");
            } else {
                break;
            }
        }
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
                    editContact(contactsList);
                    break;
                case 5:
                    deleteContact(contactsList);
                    break;
                case 6:
                    saveContacts(contactsPath, contactsList);
                    break;
                case 7:
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