package com.example.final_project;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class ContactOrganizerView {

    private final TextField nameField;
    private final TextField phoneField;
    private final TextField primaryEmailField;
    private final TextField secondaryEmailField;
    private final Button addButton;
    private final Button deleteButton;
    private final TableView<Contact> tableView;
    private ContactOrganizerController controller;  // Add this line


    public ContactOrganizerView(Stage stage) {
//        this.controller = controller;
        // Create labels and text fields for name, phone number, primary email, and secondary email
        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        Label phoneLabel = new Label("Phone number:");
        phoneField = new TextField();
        Label primaryEmailLabel = new Label("Primary Email:");
        primaryEmailField = new TextField();
        Label secondaryEmailLabel = new Label("Secondary Email:");
        secondaryEmailField = new TextField();

        // Create buttons for adding and deleting contacts
        // Create buttons for adding and deleting contacts
        addButton = new Button("Add");
        addButton.getStyleClass().add("add-button");

        deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete-button");


        // Create a spacer Region
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Add buttons and spacer to HBox
        HBox hbox = new HBox(addButton, spacer, deleteButton);
        hbox.setSpacing(10); // Adds some space between the elements


        // Create a table view to display the contacts
        tableView = new TableView<>();
        tableView.setEditable(true);


        TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nameColumn.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            controller.saveContacts();  // Add this line
        });

        TableColumn<Contact, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        phoneColumn.setOnEditCommit(event -> {
            event.getRowValue().setPhone(event.getNewValue());
            controller.saveContacts();  // Add this line
        });
        phoneColumn.setPrefWidth(150); // Set the preferred width

        TableColumn<Contact, String> primaryEmailColumn = new TableColumn<>("Primary Email");
        primaryEmailColumn.setCellValueFactory(cellData -> cellData.getValue().primaryEmailProperty());
        primaryEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        primaryEmailColumn.setOnEditCommit(event -> {
            event.getRowValue().setPrimaryEmail(event.getNewValue());
            controller.saveContacts();  // Add this line
        });
        primaryEmailColumn.setPrefWidth(200); // Set the preferred width

        TableColumn<Contact, String> secondaryEmailColumn = new TableColumn<>("Secondary Email");
        secondaryEmailColumn.setCellValueFactory(cellData -> cellData.getValue().secondaryEmailProperty());
        secondaryEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        secondaryEmailColumn.setOnEditCommit(event -> {
            event.getRowValue().setSecondaryEmail(event.getNewValue());
            controller.saveContacts();  // Add this line
        });
        secondaryEmailColumn.setPrefWidth(200); // Set the preferred width

        nameColumn.setEditable(true);
        phoneColumn.setEditable(true);
        primaryEmailColumn.setEditable(true);
        secondaryEmailColumn.setEditable(true);

        tableView.getColumns().addAll(nameColumn, phoneColumn, primaryEmailColumn, secondaryEmailColumn);

        // Create a VBox to hold the labels, text fields, buttons, and table view
        // Create a VBox to hold the labels, text fields, buttons, and table view
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                tableView,
                nameLabel, nameField,
                phoneLabel, phoneField,
                primaryEmailLabel, primaryEmailField,
                secondaryEmailLabel, secondaryEmailField,
                hbox  // Add hbox here instead of addButton and deleteButton separately
        );


        // Create a scene with the VBox as the root node
        Scene scene = new Scene(vbox, 600, 400);

        // Add the CSS file to the scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/contact_view.css")).toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Contact Organizer");
        stage.show();
    }

    // Rest of the code remains the same


    public String getName() {
        return nameField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getPrimaryEmail() {
        return primaryEmailField.getText();
    }

    public String getSecondaryEmail() {
        return secondaryEmailField.getText();
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public TableView<Contact> getTableView() {
        return tableView;
    }

    public void addContact(Contact contact) {
        tableView.getItems().add(contact);
        controller.saveContacts();  // Add this line
    }

    public void deleteContact(Contact contact) {
        tableView.getItems().remove(contact);
        controller.saveContacts();  // Add this line
    }

    public void setAddButtonHandler(Runnable handler) {
        addButton.setOnAction(event -> handler.run());
    }

    public void setDeleteButtonHandler(Runnable handler) {
        deleteButton.setOnAction(event -> handler.run());
    }
    public void setController(ContactOrganizerController controller) {
        this.controller = controller;
    }

}
