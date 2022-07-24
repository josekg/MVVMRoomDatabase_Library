package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import androidx.annotation.NonNull;

import java.util.List;


public class StudentViewModel extends AndroidViewModel {

    // calling repository tasks and
    // sending the results to the Activity
    private StudentRepository studentRepository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);

    }
    //calls repository to insert a book
    public void insert(Student student) {
        studentRepository.insert(student);
    }

    public void update(Student student) {
        studentRepository.update(student);
    }

    public void update(int bookId, int userId) { studentRepository.update(bookId, userId);}

    boolean checkValidLogin(int username, String password)
    {
        return studentRepository.isValidAccount(username, password);
    }

    //returns query results as live data object
    LiveData<Student> getStudent(int n) { return studentRepository.getStudent(n); }


}
