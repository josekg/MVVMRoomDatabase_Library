package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BooksDao {

    @Query("select * from Books")
    LiveData<List<Books>> getBooks();

    @Query("select * from Books where bookName like :name")
    LiveData<Books> getBorrowedBook(String name);

    @Query("select * from Books where category = 'Fiction'")
    LiveData<List<Books>> getFictionBooks();

    @Query("select * from Books where category = 'NonFiction'")
    LiveData<List<Books>> getNonFictionBooks();

    @Query("select * from Books where category = 'Educational'")
    LiveData<List<Books>> getEducationalBooks();

    @Query("select * from Books where category = 'History'")
    LiveData<List<Books>> getHistoryBooks();

    @Query("SELECT * from Books where bookId = :id ")
    Books getBook(int id);

    @Insert
    void insert(Books book);

    @Update
    void update(Books book);

    @Delete
    void delete(Books book);

}
