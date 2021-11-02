package com.sudiptacseseu.self.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudiptacseseu.self.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.sudiptacseseu.self.model.Journal;

public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.ViewHolder> {
   private Context context;
   private List<Journal> journalList;

    public JournalRecyclerAdapter(Context context, List<Journal> journalList) {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public JournalRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.journal_row, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalRecyclerAdapter.ViewHolder viewHolder, int position) {

        Journal journal = journalList.get(position);
        String imageUrl;

        viewHolder.titleTextView.setText(journal.getTitle());
        viewHolder.thoughtsTextView.setText(journal.getThought());
        viewHolder.nameTextView.setText(journal.getUserName());
        imageUrl = journal.getImageUrl();
        //1 hour ago..
        //Source: https://medium.com/@shaktisinh/time-a-go-in-android-8bad8b171f87
        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(journal
                .getTimeAdded()
                .getSeconds() * 1000);
        viewHolder.dateAddedTextView.setText(timeAgo);

        /*
         Use Picasso library to download and show image
         */
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.image_one)
                .fit()
                .into(viewHolder.journalImageView);

    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView
                titleTextView,
                thoughtsTextView,
                dateAddedTextView,
                nameTextView;
        public ImageView journalImageView;
        public ImageButton shareImageButton;
        String userId;
        String username;


        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            titleTextView = itemView.findViewById(R.id.journalTitleTextViewId);
            thoughtsTextView = itemView.findViewById(R.id.journalThoughtTextViewId);
            dateAddedTextView = itemView.findViewById(R.id.journalTimestampTextViewId);
            journalImageView = itemView.findViewById(R.id.journalImageViewId);
            nameTextView = itemView.findViewById(R.id.journalUsernameTextViewId);

            shareImageButton = itemView.findViewById(R.id.journal_row_share_button);
            shareImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      //context.startActivity();
                }
            });
        }
    }
}
