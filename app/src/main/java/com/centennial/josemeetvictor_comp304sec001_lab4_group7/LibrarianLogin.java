package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LibrarianLogin extends AppCompatActivity {

    private EditText username_lib;
    private EditText password_lib;
    private Button button_login;
    private Button button_sign;
    private Button student_btn;

    private LibrarianViewModel librarianViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        username_lib = (EditText) findViewById(R.id.librarian_username);
        password_lib = (EditText) findViewById(R.id.librarian_password);
        button_sign = findViewById(R.id.btn_lib_signup);
        button_login = findViewById(R.id.btn_lib_login);
        student_btn = findViewById(R.id.student_btn);
        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);

        setTitle("Librarian Page");

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = username_lib.getText().toString();
                String p = password_lib.getText().toString();

                try {


                    if (p.isEmpty() || u.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Fill both camps", Toast.LENGTH_LONG).show();
                    } else {
                        boolean isValid = librarianViewModel.checkValidLogin(u, p);
                        if (isValid) {
                            Toast.makeText(getBaseContext(), "Successfully Logged In!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LibrarianLogin.this, LibrarianActivity.class);
                            startActivity(intent);
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
                Intent intent = new Intent(LibrarianLogin.this, LibrarianSignUp.class);
                startActivity(intent);
            }
        });

        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianLogin.this, StudentLogin.class);
                startActivity(intent);
            }
        });
    }


}