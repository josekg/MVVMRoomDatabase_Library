package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface StudentDao {

    // To valid account
    @Query("SELECT * FROM student WHERE student.studentID LIKE :studentID")
    Student login(int studentID);

    // To show live data of student
    @Query("SELECT * FROM student WHERE student.studentId LIKE :id")
    LiveData<Student> getStudent(int id);

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Query("UPDATE student SET bookId = :bookId WHERE studentId = :studentId")
    void update(int bookId, int studentId);
}
