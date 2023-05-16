package com.example.final_project;

import java.io.*;
import java.util.ArrayList;

public class ContactOrganizerController {

    private final ArrayList<Contact> contacts = new ArrayList<>();
    private final File contactsFile = new File("contacts.dat");
    private ContactOrganizerView view;

    public void setView(ContactOrganizerView view) {
        this.view = view;
    }
    public ContactOrganizerController(ContactOrganizerView view) {
        this.view = view;
        // Load contacts from the file
        loadContacts();

        // Set the event handlers for the "Add" and "Delete" buttons
        view.setAddButtonHandler(() -> {
            // Get the entered contact information
            String name = view.getName();
            String phone = view.getPhone();
            String primaryEmail = view.getPrimaryEmail();
            String secondaryEmail = view.getSecondaryEmail();

            if (name.isEmpty() || phone.isEmpty()) {
                // Display an error message if name or phone is empty
                // You can customize this part based on your requirements
                System.out.println("Name and phone number are required.");
                return;
            }

            // Check if the contact already exists in the list
            Contact existingContact = findContact(name, phone);
            if (existingContact != null) {
                // If the contact already exists, update its information
                existingContact.setPrimaryEmail(primaryEmail);
                existingContact.setSecondaryEmail(secondaryEmail);
            } else {
                // If the contact is new, create a new Contact object and add it to the list
                Contact contact = new Contact(name, phone, primaryEmail, secondaryEmail);
                contacts.add(contact);
                view.addContact(contact);
            }

            // Clear the text fields after adding/updating the contact
            view.getNameField().clear();
            view.getPhoneField().clear();
            view.getPrimaryEmail().chars();
            view.getSecondaryEmail().chars();

            // Save the updated contacts list to the file
            saveContacts();
        });

        view.setDeleteButtonHandler(() -> {
            // Get the selected contact from the table view and remove it from the ArrayList
            Contact selectedContact = view.getTableView().getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contacts.remove(selectedContact);

                // Update the table view after deleting the contact
                view.deleteContact(selectedContact);

                // Save the updated contacts list to the file
                saveContacts();
            }
        });
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    void saveContacts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(contactsFile))) {
            out.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadContacts() {
        if (!contactsFile.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(contactsFile))) {
            // This cast could fail with ClassCastException if the file contains data that is not a list of contacts
            ArrayList<Contact> loadedContacts = (ArrayList<Contact>) in.readObject();
            contacts.addAll(loadedContacts);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Contact findContact(String name, String phone) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name) && contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }
}
