package com.example.chatapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyviewHolder extends RecyclerView.ViewHolder {

    TextView name,msg;
    CircleImageView img;

    public MyviewHolder(@NonNull View itemView) {
        super(itemView);

       name = itemView.findViewById(R.id.yash_id);
       msg = itemView.findViewById(R.id.hii_id);
       img = itemView.findViewById(R.id.profile_logo_1_id);
    }
}
