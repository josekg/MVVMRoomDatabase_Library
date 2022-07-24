package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditBook extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    public static final String EXTRA_QUANTITY = "EXTRA_QUANTITY";
    private EditText title_b;
    private EditText author_b;
    private EditText description_b;
    private EditText quantity_b;
    private EditText category_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title_b = findViewById(R.id.edit_text_title);
        description_b = findViewById(R.id.edit_text_description);
        quantity_b = findViewById(R.id.edit_text_quantity);
        author_b = findViewById(R.id.edit_text_author);
        category_b = findViewById(R.id.edit_text_category);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit a Book");
            title_b.setText(intent.getStringExtra(EXTRA_TITLE));
            description_b.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            quantity_b.setText(String.valueOf(intent.getIntExtra(EXTRA_QUANTITY, 1)));
            author_b.setText(intent.getStringExtra(EXTRA_AUTHOR));
            category_b.setText(intent.getStringExtra(EXTRA_CATEGORY));

        }
        else
        {
            setTitle("Add a Book");
        }

    }

    private void saveBook()
    {
        String title = title_b.getText().toString();
        String author = author_b.getText().toString();
        String description = description_b.getText().toString();
        String value = quantity_b.getText().toString();
        String category = category_b.getText().toString();

        if (title.trim().isEmpty() || author.trim().isEmpty() || description.trim().isEmpty() || category.trim().isEmpty() || value.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert complete information.", Toast.LENGTH_SHORT).show();
        }
        else {

            int quantity = Integer.parseInt(value);

            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_AUTHOR, author);
            data.putExtra(EXTRA_DESCRIPTION, description);
            data.putExtra(EXTRA_CATEGORY, category);
            data.putExtra(String.valueOf(EXTRA_QUANTITY), quantity);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id!= -1)
            {
                data.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_book:
                saveBook();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}