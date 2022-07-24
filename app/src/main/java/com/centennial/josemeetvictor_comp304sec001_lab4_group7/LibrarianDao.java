package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LibrarianDao {

    @Query("Select * from Librarian where librarianId like :libId")
    Librarian login(String libId);

    @Insert
    void insert(Librarian... librarian);
}
