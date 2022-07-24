package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;


public class LibrarianViewModel extends AndroidViewModel {

    // calling repository tasks and
    // sending the results to the Activity
    private LibrarianRepository librarianRepository;

    public LibrarianViewModel(@NonNull Application application) {
        super(application);
        librarianRepository = new LibrarianRepository(application);
    }
    //calls repository to insert a book
    public void insert(Librarian librarian) {
        librarianRepository.insert(librarian);
    }

    public boolean checkValidLogin(String username, String password)
    {
        return librarianRepository.isValidAccount(username, password);
    }
    

}
