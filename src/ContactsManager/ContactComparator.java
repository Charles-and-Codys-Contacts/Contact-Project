package ContactsManager;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    public int compare(Contact a, Contact b) {
        return a.getFirstName().compareTo(b.getFirstName());
    }
}