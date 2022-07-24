package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSignUp extends AppCompatActivity {

    private EditText studentId;
    private EditText studentFirst;
    private EditText studentLast;
    private EditText password;
    private Button create;

    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        studentId = findViewById(R.id.stu_id);
        studentFirst = findViewById(R.id.stu_firstName);
        studentLast = findViewById(R.id.stu_lastName);
        password = findViewById(R.id.stu_pass);
        create = findViewById(R.id.create_student);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        setTitle("Student Page");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = studentId.getText().toString();
                String first = studentFirst.getText().toString();
                String last = studentLast.getText().toString();
                String pass = password.getText().toString();

                if (id.trim().isEmpty() || first.trim().isEmpty() || last.trim().isEmpty() || pass.trim().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Please insert complete information.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int i = Integer.parseInt(id);
                    Student student = new Student();
                    student.setStudentId(i);
                    student.setFirstName(first);
                    student.setLastName(last);
                    student.setPassword(pass);
                    studentViewModel.insert(student);
                    Toast.makeText(getBaseContext(), "Account created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentSignUp.this, StudentLogin.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.close_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.close_page:
                Intent intent = new Intent(StudentSignUp.this, StudentLogin.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}