package com.example.final_project;

import javafx.application.Application;
import javafx.stage.Stage;

public class run extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the view without passing the controller
        ContactOrganizerView view = new ContactOrganizerView(primaryStage);

        // Now, create the controller passing the view
        ContactOrganizerController controller = new ContactOrganizerController(view);

        // Finally, set the controller to the view
        view.setController(controller);

        // Populate the table view with the loaded contacts
        for (Contact contact : controller.getContacts()) {
            view.addContact(contact);
        }
    }

}
