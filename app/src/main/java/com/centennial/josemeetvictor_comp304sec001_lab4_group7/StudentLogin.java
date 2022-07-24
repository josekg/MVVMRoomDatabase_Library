package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {

    private EditText id_stu;
    private EditText password_stu;
    private Button button_login;
    private Button button_sign;
    private Button librarian_btn;
    public static final String EXTRA_USER = "EXTRA_USER";

    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        id_stu = (EditText) findViewById(R.id.student_id);
        password_stu = (EditText) findViewById(R.id.student_password);
        button_sign = findViewById(R.id.btn_stu_signup);
        button_login = findViewById(R.id.btn_stu_login);
        librarian_btn = findViewById(R.id.librarian_btn);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        setTitle("Student Page");

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = id_stu.getText().toString();
                String p = password_stu.getText().toString();


                try {
                    if (p.isEmpty() || u.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Fill both camps", Toast.LENGTH_LONG).show();
                    } else {
                        int id = Integer.parseInt(u);
                        boolean isValid = studentViewModel.checkValidLogin(id, p);
                        if (isValid) {
                            Toast.makeText(getBaseContext(), "Successfully Logged In!", Toast.LENGTH_LONG).show();
                            Intent intentStudent = new Intent(StudentLogin.this, StudentActivity.class);
                            intentStudent.putExtra(EXTRA_USER, u);
                            startActivity(intentStudent);
                        } else {
                            Toast.makeText(getBaseContext(), "Invalid Login!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(getBaseContext(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLogin.this, StudentSignUp.class);
                startActivity(intent);
            }
        });

        librarian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLogin.this, LibrarianLogin.class);
                startActivity(intent);
            }
        });
    }


}