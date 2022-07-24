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

public class LibrarianSignUp extends AppCompatActivity {

    private EditText librarianId;
    private EditText librarianFirst;
    private EditText librarianLast;
    private EditText password;
    private Button create;

    private LibrarianViewModel librarianViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_sign_up);

        librarianId = findViewById(R.id.lib_id);
        librarianFirst = findViewById(R.id.lib_firstName);
        librarianLast = findViewById(R.id.lib_lastName);
        password = findViewById(R.id.lib_pass);
        create = findViewById(R.id.create_librarian);

        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);

        setTitle("Librarian Page");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = librarianId.getText().toString();
                String first = librarianFirst.getText().toString();
                String last = librarianLast.getText().toString();
                String pass = password.getText().toString();

                if (id.trim().isEmpty() || first.trim().isEmpty() || last.trim().isEmpty() || pass.trim().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Please insert complete information.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Librarian librarian = new Librarian(id, first, last, pass);
                    librarianViewModel.insert(librarian);
                    Toast.makeText(getBaseContext(), "Account created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LibrarianSignUp.this, LibrarianActivity.class);
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
                Intent intent = new Intent(LibrarianSignUp.this, LibrarianLogin.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}