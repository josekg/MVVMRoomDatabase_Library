package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.lifecycle.LiveData;
import android.content.Context;
import java.util.List;

public class BooksRepository {

    private BooksDao bookDao;
    private LiveData<List<Books>> bookList;
    private LiveData<List<Books>> fictionList;
    private LiveData<List<Books>> nonFictionList;
    private LiveData<List<Books>> educList;
    private LiveData<List<Books>> historyList;
    private LiveData<Books> borrowedBook;
    private Books book;

    public BooksRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        bookDao = db.booksDao();
        //call interface method
        bookList = bookDao.getBooks();
        fictionList = bookDao.getFictionBooks();
        nonFictionList = bookDao.getNonFictionBooks();
        historyList = bookDao.getHistoryBooks();
        educList = bookDao.getEducationalBooks();
    }

    LiveData<Books> getBorrowedBook(String name)
    {
        borrowedBook = bookDao.getBorrowedBook(name);
        return borrowedBook;
    }

    Books getBook(int id)
    {
        book = bookDao.getBook(id);
        return book;
    }

    // returns query results as LiveData object
    LiveData<List<Books>> getBooks() {return bookList;}

    LiveData<List<Books>> getFictionBooks() {return fictionList;}

    LiveData<List<Books>> getEducList() {return educList;}

    LiveData<List<Books>> getNonFictionList() {return nonFictionList;}

    LiveData<List<Books>> getHistoryList() {return historyList;}

    //inserts a book asynchronously
    public void insert(Books book) {
        insertAsync(book);
    }
    //Updates a book async

    public void update(Books book) { updateAsync(book);}
//    //deletes a book asynchronously
    public void delete(Books book) { deleteAsync(book); }

    //Insert and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void insertAsync(final Books book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.insert(book);
                    //insertResult.postValue(1);
                } catch (Exception e) {
                    //insertResult.postValue(0);
                }
            }
        }).start();
    }

    private void updateAsync(final Books book) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.update(book);
                    //deleteResult.postValue(1);
                } catch (Exception e) {
                    //deleteResult.postValue(0);
                }
            }
        }).start();
    }

    private void deleteAsync(final Books book) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bookDao.delete(book);
                    //deleteResult.postValue(1);
                } catch (Exception e) {
                    //deleteResult.postValue(0);
                }
            }
        }).start();
    }
}
