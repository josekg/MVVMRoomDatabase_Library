package com.centennial.josemeetvictor_comp304sec001_lab4_group7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<Books> books = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Books currentBook = books.get(position);
        holder.textViewTitle.setText(currentBook.getBookName());
        holder.textViewAuthor.setText(currentBook.getAuthorName());
        holder.textViewDescription.setText(currentBook.getBookDescription());
        holder.textViewCategory.setText(currentBook.getCategory());
        holder.textViewQuantity.setText(String.valueOf(currentBook.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Books> books)
    {
        this.books = books;
        notifyDataSetChanged();
    }

    public Books getBookAt(int position)
    {
        return books.get(position);
    }


    class BookHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewAuthor;
        private TextView textViewDescription;
        private TextView textViewQuantity;
        private TextView textViewCategory;

        public BookHolder(View itemView)
        {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_title);
            textViewAuthor = itemView.findViewById(R.id.text_author);
            textViewDescription = itemView.findViewById(R.id.text_description);
            textViewQuantity = itemView.findViewById(R.id.text_quantity);
            textViewCategory = itemView.findViewById(R.id.text_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(books.get(position));
                    }
                }
            });


        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Books book);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;

    }

}
