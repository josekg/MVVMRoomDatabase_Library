package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BorrowBook extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    public static final String EXTRA_QUANTITY = "EXTRA_QUANTITY";
    public static final String PROFILE_BOOK_NAME = "PROFILE_BOOK_NAME";
    public static final String USER_NUMBER = "USER_NUMBER";
    public static final String PROFILE_USER_ID = "PROFILE_USER_ID";
    private TextView bookName;
    private TextView bookAuthor;
    private TextView bookDescription;
    private TextView bookCategory;
    private TextView bookQuantity;
    private Button borrow;

    private BooksViewModel booksViewModel;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        bookName = findViewById(R.id.book_name);
        bookAuthor = findViewById(R.id.book_author);
        bookDescription = findViewById(R.id.book_desc);
        bookCategory = findViewById(R.id.book_category);
        bookQuantity = findViewById(R.id.book_quantity);
        borrow = findViewById(R.id.borrow_btn);

        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        Intent intent = getIntent();

        bookName.setText(intent.getStringExtra(EXTRA_TITLE));
        bookDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        bookQuantity.setText("Quantity: "+ String.valueOf(intent.getIntExtra(EXTRA_QUANTITY, 1)));
        bookAuthor.setText(intent.getStringExtra(EXTRA_AUTHOR));
        bookCategory.setText(intent.getStringExtra(EXTRA_CATEGORY));
        String user = intent.getStringExtra(USER_NUMBER);

        setTitle("Borrow a Book");

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = intent.getIntExtra(BorrowBook.EXTRA_ID, -1);
                String title = intent.getStringExtra(BorrowBook.EXTRA_TITLE);
                String author = intent.getStringExtra(BorrowBook.EXTRA_AUTHOR);
                String description = intent.getStringExtra(BorrowBook.EXTRA_DESCRIPTION);
                String category = intent.getStringExtra(BorrowBook.EXTRA_CATEGORY);
                int quantity = intent.getIntExtra(String.valueOf(BorrowBook.EXTRA_QUANTITY), 1);

                int u = Integer.parseInt(user);

                Books book = new Books();
                book.setBookName(title);
                book.setBookDescription(description);
                book.setAuthorName(author);
                book.setCategory(category);
                book.setQuantity(quantity - 1);
                book.setBookId(id);
                booksViewModel.update(book);
                studentViewModel.update(id, u);

                Toast.makeText(getBaseContext(), "Book Borrowed", Toast.LENGTH_SHORT).show();

                Intent data = new Intent(BorrowBook.this, StudentActivity.class);
                data.putExtra(PROFILE_BOOK_NAME, title);
                data.putExtra(PROFILE_USER_ID, user);
                startActivity(data);
            }
        });
    }
}