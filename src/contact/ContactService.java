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
        System.out.println("Choose saving place: ");
        System.out.println("1=> Phone");

        SimCard[] simCards = phone.getSimCards();
        System.out.println("2=> " + simCards[0].getName());

        boolean secondHas = simCards[1] != null;
        if (secondHas) System.out.println("3=> " + simCards[1].getName());


        int com = scanner.nextInt();
        if (com < 1 || !secondHas && com > 2 || com > 3) {
            System.err.println("Choose correct place");
            add();
            return;
        }

        if (com == 1) insertContact(phone.getContacts());
        else if (com == 2) insertContact(simCards[0].getContacts());
        else insertContact(simCards[1].getContacts());
    }

    public void edit() {
    }

    public void list() {
    }

    public void search() {
    }

    public void delete() {

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
