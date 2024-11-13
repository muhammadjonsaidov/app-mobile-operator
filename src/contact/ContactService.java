package contact;

import phone.Phone;
import simcard.SimCard;

import java.util.Scanner;

public class ContactService {

    private static Scanner scanner = new Scanner(System.in);

    private final Phone phone;

    public ContactService(Phone phone) {
        this.phone = phone;
    }

    public void add() {
        while (true) {
            System.out.println("Choose saving place: ");
            System.out.println("1=> Phone");

            SimCard[] simCards = phone.getSimCards();
            System.out.println("2=> " + simCards[0].getName());

            boolean secondHas = simCards[1] != null;
            if (secondHas) System.out.println("3=> " + simCards[1].getName());
            System.out.println("0=> Back to main menu");

            int com = scanner.nextInt();
            scanner = new Scanner(System.in);
            if (com < 0 || !secondHas && com > 2 || com > 3) {
                System.err.println("Choose correct place");
                add();
                return;
            }

            switch (com) {
                case 1 -> insertContact(phone.getContacts());
                case 2 -> insertContact(simCards[0].getContacts());
                case 3 -> insertContact(simCards[1].getContacts());
                case 0 -> {
                    return;
                }
            }

        }
    }

    public void edit() {
    }

    public void list() {
        // todo: contact list phone | sim card 1 | sim card 2 if it exists
        while (true) {
            System.out.println("Select a place to view contact information: ");
            System.out.println("1=> Phone");

            SimCard[] simCards = phone.getSimCards();
            System.out.println("2=> " + simCards[0].getName());

            boolean secondHas = simCards[1] != null;
            if (secondHas) System.out.println("3=> " + simCards[1].getName());
            System.out.println("0=> Back to previous section");

            int com = scanner.nextInt();
            scanner = new Scanner(System.in);

            if (com < 0 || !secondHas && com > 2 || com > 3) {
                System.err.println("Choose correct place");
                list();
                return;
            }

            switch (com) {
                case 1 -> {
                    System.out.println("Contacts in Phone:");
                    printContacts(phone.getContacts());
                }
                case 2 -> {
                    System.out.println("Contacts in " + simCards[0].getName() + ":");
                    printContacts(simCards[0].getContacts());
                }
                case 3 -> {
                    System.out.println("Contacts in " + simCards[1].getName() + ":");
                    printContacts(simCards[1].getContacts());
                }
                case 0 -> {
                    return;
                }
            }
        }

    }

    public void search() {
    }

    public void delete() {

    }

    private void printContacts(Contact[] contacts) {
        boolean hasContacts = false;
        for (int i = 0; i < contacts.length; i++)
            if (contacts[i] != null) {
                System.out.println((i + 1) + ". " + contacts[i].getName() + ": " + contacts[i].getNumber());
                hasContacts = true;
            }

        if (!hasContacts) {
            System.out.println("No contacts found.");
        }
    }

    private void insertContact(Contact[] contacts, Contact contact) {
        for (int i = 0; i < contacts.length; i++)
            if (contacts[i] == null) {
                contacts[i] = contact;
                System.out.println("Contact added successfully!");
                return;
            }

        System.out.println("No space to add contact.");
    }

    private boolean checkName(Contact[] contacts, Contact contact) {
        for (Contact existingContact : contacts)
            if (existingContact != null && existingContact.getName().equals(contact.getName()))
                return false;

        return true;
    }

    private Contact getContactInfoFromConsole() {
        System.out.println("Enter contact name:");
        String name = scanner.nextLine();

        System.out.println("Enter contact number:");
        String number = scanner.nextLine();

        return new Contact(name, number);
    }

    private boolean checkSpace(Contact[] contacts) {
        for (Contact contact : contacts)
            if (contact == null) return true;

        return false;
    }

    private void insertContact(Contact[] contacts) {
        if (!(checkSpace(contacts))) {
            System.out.println("No space on the phone");
            add();
            return;
        }

        Contact contact = getContactInfoFromConsole();
        if (!(checkName(contacts, contact))) {
            System.out.println("Name of contact already exists");
            add();
            return;
        }

        insertContact(contacts, contact);
    }
}
