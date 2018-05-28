package com.example.sripadpc.collegeapp;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.sripadpc.collegeapp.R.id.textView13;

/**
 * Created by sripadpc on 27-05-2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    Context Cx;
    List<NoticeData> Lists;

    public ImageAdapter(Context cx, List<NoticeData> lists) {
        Cx = cx;
        Lists = lists;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Cx).inflate(R.layout.forrecycler,parent,false);
        return new ImageViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final NoticeData data = Lists.get(position);
        holder.et1.setText(data.getName());

        holder.et2.setText(data.getDate());
        Glide.with(Cx).load(data.getURL()).centerCrop().into(holder.Im1);
        holder.cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Cx, R.style.Theme_Dialog);
                dialog.setContentView(R.layout.dialog);
                ImageView imageView = dialog.findViewById(R.id.imageView7);
                Glide.with(Cx).load(data.getURL()).fitCenter().into(imageView);
                dialog.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return Lists.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        TextView et1,et2;
        ImageView Im1;
        CardView cview;
        @SuppressLint("WrongViewCast")
        public ImageViewHolder(View itemView) {
            super(itemView);
            et1 = itemView.findViewById(R.id.textView13);
            et2 = itemView.findViewById(R.id.textView14);
            Im1 = itemView.findViewById((R.id.imageView12));
            cview = itemView.findViewById(R.id.CardView);
        }
    }


    }

