package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StudentProfile extends AppCompatActivity {

    private TextView profileId;
    private TextView profileFirst;
    private TextView profileLast;
    private TextView profileBook;
    private TextView profileBookId;

    StudentViewModel studentViewModel;
    BooksViewModel booksViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        profileId = findViewById(R.id.profile_id);
        profileFirst = findViewById(R.id.profile_first);
        profileLast = findViewById(R.id.profile_last);
        profileBook = findViewById(R.id.profile_bookName);
        profileBookId = findViewById(R.id.profile_bookiD);

        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        Intent intent = getIntent();
        String name = intent.getStringExtra(StudentActivity.PROFILE_BOOK_NAME_ACT);
        int id = Integer.parseInt(intent.getStringExtra(StudentActivity.PROFILE_USER_ID));
        if (name != null) {
            studentViewModel.getStudent(id).observe(this, new Observer<Student>() {
                @Override
                public void onChanged(Student student) {
                    profileId.setText("Student Id: " + student.getStudentId());
                    profileFirst.setText("First Name: " + student.getFirstName());
                    profileLast.setText("Last Name: " + student.getLastName());
                    profileBookId.setText("Book Id: " + student.getBookId());
                }
            });

            booksViewModel.getBorrowedBook(name).observe(this, new Observer<Books>() {
                @Override
                public void onChanged(Books books) {

                    profileBook.setText(books.getBookName());
                }
            });
        } else
        {
            studentViewModel.getStudent(id).observe(this, new Observer<Student>() {
                @Override
                public void onChanged(Student student) {
                    profileId.setText("Student Id: " + student.getStudentId());
                    profileFirst.setText("First Name: " + student.getFirstName());
                    profileLast.setText("Last Name: " + student.getLastName());
                    profileBookId.setText("Book Id: " + student.getBookId());

                    Books book = booksViewModel.getBook(id);
                    String b = book.getBookName();
                    profileBook.setText(b);
                }
            });
        }
        }

}