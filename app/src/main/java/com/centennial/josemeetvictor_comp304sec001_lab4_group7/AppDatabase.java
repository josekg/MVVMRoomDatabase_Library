package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Books.class, Student.class, Librarian.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    //
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "LibraryDB";
    public abstract BooksDao booksDao();
    public abstract LibrarianDao librarianDao();
    public abstract StudentDao studentDao();

    //Singleton Pattern to have one instance of DB
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
