package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.lifecycle.LiveData;
import android.content.Context;
import java.util.List;

public class StudentRepository {

    private final StudentDao studentsDao;
    private LiveData<Student> studentShow;
    public StudentRepository(Context context) {
        // create DB object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        studentsDao = db.studentDao();

    }

    // returns query results as LiveData object
    LiveData<Student> getStudent(int id) {
        studentShow = studentsDao.getStudent(id);
        return studentShow;
    }

    //inserts a person asynchronously
    public void insert(Student students) {
        insertAsync(students);
    }

    public void update(Student student) { updateAsync(student);}

    public void update(int bookId, int student) { updateAsync(bookId, student);}

    public boolean isValidAccount(int iD, final String password)
    {
        Student student = studentsDao.login(iD);
        return student.getPassword().equals(password);
    }
    //Insert and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void insertAsync(final Student students) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentsDao.insert(students);
                    //insertResult.postValue(1);
                } catch (Exception e) {
                    //insertResult.postValue(0);
                }
            }
        }).start();
    }

    private void updateAsync(final Student student) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentsDao.update(student);
                    //deleteResult.postValue(1);
                } catch (Exception e) {
                    //deleteResult.postValue(0);
                }
            }
        }).start();
    }

    private void updateAsync(final int bookId, final int stuId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentsDao.update(bookId, stuId);
                    //deleteResult.postValue(1);
                } catch (Exception e) {
                    //deleteResult.postValue(0);
                }
            }
        }).start();
    }
}
