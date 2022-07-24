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
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LibrarianActivity extends AppCompatActivity {

    public static final int ADD_BOOK_REQUEST = 1;
    public static final int EDIT_BOOK_REQUEST = 2;

    private BooksViewModel booksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);

        FloatingActionButton btnAddBook = findViewById(R.id.btn_add);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                startActivityForResult(intent, ADD_BOOK_REQUEST);
            }
        });

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                booksViewModel.delete(adapter.getBookAt(viewHolder.getAdapterPosition()));
                Toast.makeText(LibrarianActivity.this, "Book Deleted.", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Books book) {
                Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                intent.putExtra(AddEditBook.EXTRA_ID, book.getBookId());
                intent.putExtra(AddEditBook.EXTRA_TITLE, book.getBookName());
                intent.putExtra(AddEditBook.EXTRA_AUTHOR, book.getAuthorName());
                intent.putExtra(AddEditBook.EXTRA_DESCRIPTION, book.getBookDescription());
                intent.putExtra(AddEditBook.EXTRA_CATEGORY, book.getCategory());
                intent.putExtra(AddEditBook.EXTRA_QUANTITY, book.getQuantity());
                startActivityForResult(intent, EDIT_BOOK_REQUEST);

            }
        });

        setTitle("Centennial College Library");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BOOK_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditBook.EXTRA_TITLE);
            String author = data.getStringExtra(AddEditBook.EXTRA_AUTHOR);
            String description = data.getStringExtra(AddEditBook.EXTRA_DESCRIPTION);
            String category = data.getStringExtra(AddEditBook.EXTRA_CATEGORY);
            int quantity = data.getIntExtra(String.valueOf(AddEditBook.EXTRA_QUANTITY), 1);

            Books book = new Books();
            book.setBookName(title);
            book.setBookDescription(description);
            book.setAuthorName(author);
            book.setCategory(category);
            book.setQuantity(quantity);

            booksViewModel.insert(book);

            Toast.makeText(this, "Book Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_BOOK_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditBook.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Book can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditBook.EXTRA_TITLE);
            String author = data.getStringExtra(AddEditBook.EXTRA_AUTHOR);
            String description = data.getStringExtra(AddEditBook.EXTRA_DESCRIPTION);
            String category = data.getStringExtra(AddEditBook.EXTRA_CATEGORY);
            int quantity = data.getIntExtra(String.valueOf(AddEditBook.EXTRA_QUANTITY), 1);

            Books book = new Books();
            book.setBookName(title);
            book.setBookDescription(description);
            book.setAuthorName(author);
            book.setCategory(category);
            book.setQuantity(quantity);
            book.setBookId(id);
            booksViewModel.update(book);
            Toast.makeText(this, "Book updated", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "Book not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.librarian_options, menu);
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
                        Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                        intent.putExtra(AddEditBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(AddEditBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(AddEditBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(AddEditBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(AddEditBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(AddEditBook.EXTRA_QUANTITY, book.getQuantity());
                        startActivityForResult(intent, EDIT_BOOK_REQUEST);

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
                        Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                        intent.putExtra(AddEditBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(AddEditBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(AddEditBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(AddEditBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(AddEditBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(AddEditBook.EXTRA_QUANTITY, book.getQuantity());
                        startActivityForResult(intent, EDIT_BOOK_REQUEST);

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
                        Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                        intent.putExtra(AddEditBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(AddEditBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(AddEditBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(AddEditBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(AddEditBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(AddEditBook.EXTRA_QUANTITY, book.getQuantity());
                        startActivityForResult(intent, EDIT_BOOK_REQUEST);

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
                        Intent intent = new Intent(LibrarianActivity.this, AddEditBook.class);
                        intent.putExtra(AddEditBook.EXTRA_ID, book.getBookId());
                        intent.putExtra(AddEditBook.EXTRA_TITLE, book.getBookName());
                        intent.putExtra(AddEditBook.EXTRA_AUTHOR, book.getAuthorName());
                        intent.putExtra(AddEditBook.EXTRA_DESCRIPTION, book.getBookDescription());
                        intent.putExtra(AddEditBook.EXTRA_CATEGORY, book.getCategory());
                        intent.putExtra(AddEditBook.EXTRA_QUANTITY, book.getQuantity());
                        startActivityForResult(intent, EDIT_BOOK_REQUEST);

                    }
                });
                return true;
            case R.id.logout:
                Intent intent = new Intent(LibrarianActivity.this, LibrarianLogin.class);
                startActivity(intent);
                Toast.makeText(this, "Sign Out Completed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "No Books added", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);
        }
    }
}