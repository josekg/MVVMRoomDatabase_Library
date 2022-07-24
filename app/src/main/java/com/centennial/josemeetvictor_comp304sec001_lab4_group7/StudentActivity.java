package com.centennial.josemeetvictor_comp304sec001_lab4_group7;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    public static final String PROFILE_USER_ID = "PROFILE_USER";
    public static final String PROFILE_BOOK_NAME_ACT = "PROFILE_BOOK_NAME";
    public String bookName;
    public String userId;
    String userProfile;

    private BooksViewModel booksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(List<Books> books) {
                // update RecyclerView
                adapter.setBooks(books);
            }
        });

        Intent intent = getIntent();
        bookName = intent.getStringExtra(BorrowBook.PROFILE_BOOK_NAME);
        userId = intent.getStringExtra(StudentLogin.EXTRA_USER);
        userProfile = intent.getStringExtra(BorrowBook.PROFILE_USER_ID);

            adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Books book) {
                    Intent intent = new Intent(StudentActivity.this, BorrowBook.class);
                    intent.putExtra(BorrowBook.EXTRA_ID, book.getBookId());
                    intent.putExtra(BorrowBook.EXTRA_TITLE, book.getBookName());
                    intent.putExtra(BorrowBook.EXTRA_AUTHOR, book.getAuthorName());
                    intent.putExtra(BorrowBook.EXTRA_DESCRIPTION, book.getBookDescription());
                    intent.putExtra(BorrowBook.EXTRA_CATEGORY, book.getCategory());
                    intent.putExtra(BorrowBook.EXTRA_QUANTITY, book.getQuantity());
                    intent.putExtra(BorrowBook.USER_NUMBER, userId);
                    if (bookName == null) {
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(), "Book already borrowed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        setTitle("Centennial College Library");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cat_fiction:
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);

                BookAdapter adapter = new BookAdapter();
                recyclerView.setAdapter(adapter);

                booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
                booksViewModel.getFictionBooks().observe(this, new Observer<List<Books>>() {
                    @Override
                    public void onChanged(List<Books> books) {
                        adapter.setBooks(books);
                    }
                });
                adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Books book) {
                        Intent intent = new Intent(StudentActivity.this, BorrowBook.class);
                        intent.putExtra(BorrowBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(BorrowBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(BorrowBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(BorrowBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(BorrowBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(BorrowBook.EXTRA_QUANTITY, book.getQuantity());
                        intent.putExtra(BorrowBook.USER_NUMBER, userId);
                        if (bookName != null) {
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "Book already borrowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            case R.id.cat_nonfic:
                RecyclerView recyclerViewNonFic = findViewById(R.id.recycler_view);
                recyclerViewNonFic.setLayoutManager(new LinearLayoutManager(this));
                recyclerViewNonFic.setHasFixedSize(true);

                BookAdapter adapterNon = new BookAdapter();
                recyclerViewNonFic.setAdapter(adapterNon);

                booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
                booksViewModel.getNonFictionBooks().observe(this, new Observer<List<Books>>() {
                    @Override
                    public void onChanged(List<Books> books) {
                        adapterNon.setBooks(books);
                    }
                });
                adapterNon.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Books book) {
                        Intent intent = new Intent(StudentActivity.this, BorrowBook.class);
                        intent.putExtra(BorrowBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(BorrowBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(BorrowBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(BorrowBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(BorrowBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(BorrowBook.EXTRA_QUANTITY, book.getQuantity());
                        intent.putExtra(BorrowBook.USER_NUMBER, userId);
                        if (bookName == null) {
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "Book already borrowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            case R.id.cat_educ:
                RecyclerView recyclerViewEduc = findViewById(R.id.recycler_view);
                recyclerViewEduc.setLayoutManager(new LinearLayoutManager(this));
                recyclerViewEduc.setHasFixedSize(true);

                BookAdapter adapterEduc = new BookAdapter();
                recyclerViewEduc.setAdapter(adapterEduc);

                booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
                booksViewModel.getEducationalBooks().observe(this, new Observer<List<Books>>() {
                    @Override
                    public void onChanged(List<Books> books) {
                        adapterEduc.setBooks(books);
                    }
                });
                adapterEduc.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Books book) {
                        Intent intent = new Intent(StudentActivity.this, BorrowBook.class);
                        intent.putExtra(BorrowBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(BorrowBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(BorrowBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(BorrowBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(BorrowBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(BorrowBook.EXTRA_QUANTITY, book.getQuantity());
                        intent.putExtra(BorrowBook.USER_NUMBER, userId);
                        if (bookName == null) {
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "Book already borrowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            case R.id.cat_history:
                RecyclerView recyclerViewHistory = findViewById(R.id.recycler_view);
                recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
                recyclerViewHistory.setHasFixedSize(true);

                BookAdapter adapterHistory = new BookAdapter();
                recyclerViewHistory.setAdapter(adapterHistory);

                booksViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
                booksViewModel.getHistoryBooks().observe(this, new Observer<List<Books>>() {
                    @Override
                    public void onChanged(List<Books> books) {
                        adapterHistory.setBooks(books);
                    }
                });
                adapterHistory.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Books book) {
                        Intent intent = new Intent(StudentActivity.this, BorrowBook.class);
                        intent.putExtra(BorrowBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(BorrowBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(BorrowBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(BorrowBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(BorrowBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(BorrowBook.EXTRA_QUANTITY, book.getQuantity());
                        intent.putExtra(BorrowBook.USER_NUMBER, userId);
                        if (bookName == null) {
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "Book already borrowed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            case R.id.logout:
                Intent intent = new Intent(StudentActivity.this, StudentLogin.class);
                startActivity(intent);
                Toast.makeText(this, "Sign Out Completed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                if(userProfile != null) {
                    Intent intent2 = new Intent(StudentActivity.this, StudentProfile.class);
                    intent2.putExtra(PROFILE_USER_ID, userProfile);
                    intent2.putExtra(PROFILE_BOOK_NAME_ACT, bookName);
                    startActivity(intent2);
                }
                else
                {
                    Intent intent2 = new Intent(StudentActivity.this, StudentProfile.class);
                    intent2.putExtra(PROFILE_USER_ID, userId);
                    intent2.putExtra(PROFILE_BOOK_NAME_ACT, bookName);
                    startActivity(intent2);
                }
                Toast.makeText(this, "Profile Page", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "No Books added", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }
}