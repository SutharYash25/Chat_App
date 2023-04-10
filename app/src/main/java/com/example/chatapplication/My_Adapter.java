package com.example.chatapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class My_Adapter extends RecyclerView.Adapter<MyviewHolder> {
     ArrayList<Modal> list;

    public My_Adapter(ArrayList<Modal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_file,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.img.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.msg.setText(list.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
