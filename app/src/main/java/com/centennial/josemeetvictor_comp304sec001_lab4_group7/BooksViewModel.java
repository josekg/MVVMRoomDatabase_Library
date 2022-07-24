package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import java.util.List;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class BooksViewModel extends AndroidViewModel {

    // calling repository tasks and
    // sending the results to the Activity
    private BooksRepository booksRepository;
    private LiveData<List<Books>> allBooks;
    private LiveData<List<Books>> fictionBooks;
    private LiveData<List<Books>> nonFictionBooks;
    private LiveData<List<Books>> historyBooks;
    private LiveData<List<Books>> educationalBooks;

    public BooksViewModel(@NonNull Application application) {
        super(application);
        booksRepository = new BooksRepository(application);
        allBooks = booksRepository.getBooks();
        fictionBooks = booksRepository.getFictionBooks();
        educationalBooks = booksRepository.getEducList();
        nonFictionBooks = booksRepository.getNonFictionList();
        historyBooks = booksRepository.getHistoryList();
    }
    //calls repository to insert a book
    public void insert(Books book) {
        booksRepository.insert(book);
    }

    //calls repository to update a book
    public void update(Books book) {
        booksRepository.update(book);
    }

    //calls repository to delete a book
    public void delete(Books book) { booksRepository.delete(book); }

    //returns query results as live data object
    LiveData<List<Books>> getAllBooks() { return allBooks; }

    LiveData<List<Books>> getFictionBooks() {return fictionBooks;}

    LiveData<List<Books>> getNonFictionBooks() {return nonFictionBooks;}

    LiveData<List<Books>> getEducationalBooks() {return educationalBooks;}

    LiveData<List<Books>> getHistoryBooks() {return historyBooks;}

    LiveData<Books> getBorrowedBook(String name)
    {
        return booksRepository.getBorrowedBook(name);
    }

    Books getBook(int id) { return booksRepository.getBook(id);}



}
