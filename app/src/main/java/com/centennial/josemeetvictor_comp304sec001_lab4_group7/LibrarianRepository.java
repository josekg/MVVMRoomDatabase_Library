package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class LibrarianRepository {

    private final LibrarianDao librarianDao;

    public LibrarianRepository(Context context) {
        // create DB object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        librarianDao = db.librarianDao();
    }

    public boolean isValidAccount(String username, final String password)
    {
       Librarian librarian = librarianDao.login(username);
       return librarian.getPassword().equals(password);
    }
    //inserts a person asynchronously
    public void insert(Librarian librarian) {
        insertAsync(librarian);
    }

    //Insert and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void insertAsync(final Librarian librarian) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    librarianDao.insert(librarian);
                    //insertResult.postValue(1);
                } catch (Exception e) {
                    //insertResult.postValue(0);
                }
            }
        }).start();
    }
}