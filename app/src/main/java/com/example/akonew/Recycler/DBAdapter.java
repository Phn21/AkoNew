package com.example.akonew.Recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akonew.R;
import com.example.akonew.Room.Db;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.DbHolder> {

    private List<Db> dbList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DbHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new DbHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbHolder holder, int position) {
        Db db = dbList.get(position);
        holder.name.setText(db.getName());
        holder.surname.setText(db.getSurname());
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public void setData(List<Db> dbs) {
        this.dbList = dbs;
        notifyDataSetChanged();
    }

    public Db getDataAt(int position) {
        return dbList.get(position);
    }

    public class DbHolder extends RecyclerView.ViewHolder {

        private TextView name, surname;

        public DbHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            surname = itemView.findViewById(R.id.textSurname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(dbList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Db db);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
