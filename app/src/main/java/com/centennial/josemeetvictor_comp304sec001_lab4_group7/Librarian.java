package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Librarian {
    @PrimaryKey @NonNull
    private String librarianId;
    private String firstName;
    private String lastName;
    private String password;

    public Librarian(String libId, String first, String last, String pass) {
        this.librarianId = libId;
        this.firstName = first;
        this.lastName = last;
        this.password = pass;
    }

    public Librarian () {}


    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
