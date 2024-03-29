package com.example.mvvvmnotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import java.security.PrivilegedAction;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) { // id
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {  // title, description
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                            oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.mTitle.setText(note.getTitle());
        holder.mDesc.setText(note.getDescription());
        holder.mPriority.setText(String.valueOf(note.getPriority()));
    }

    public Note getNote(int position) {
        return getItem(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDesc, mPriority;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.tv_title);
            mDesc = itemView.findViewById(R.id.tv_Description);
            mPriority = itemView.findViewById(R.id.tv_priority);
        }
    }

}
