package com.example.final_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serial;
import java.io.Serializable;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;
    private String primaryEmail;
    private String secondaryEmail;

    public Contact(String name, String phone, String primaryEmail, String secondaryEmail) {
        this.name = name;
        this.phone = phone;
        this.primaryEmail = primaryEmail;
        this.secondaryEmail = secondaryEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // These methods are not serializable but they are not holding any state to be serialized
    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty phoneProperty() {
        return new SimpleStringProperty(phone);
    }

    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public StringProperty primaryEmailProperty() {
        return new SimpleStringProperty(primaryEmail);
    }

    public StringProperty secondaryEmailProperty() {
        return new SimpleStringProperty(secondaryEmail);
    }
}
