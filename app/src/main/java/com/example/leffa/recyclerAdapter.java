package com.example.leffa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<theatreInfo> Infot;

    public recyclerAdapter(ArrayList<theatreInfo> Infot) {
        this.Infot = Infot;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameText;
        protected TextView timeText;

        public MyViewHolder(final View view) {
            super(view);
            nameText = view.findViewById(R.id.textView3);
            timeText = view.findViewById(R.id.textView5);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = Infot.get(position).getMovieName();
        holder.nameText.setText(name);
        String time = Infot.get(position).getMovieStartTime();
        holder.timeText.setText(time);
    }

    @Override
    public int getItemCount() {
        return Infot.size();
    }
}

