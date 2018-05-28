package com.example.sripadpc.collegeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sripadpc on 28-05-2018.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocmentHolder> {
   private Context Cx;
  private  List<NotesDataClass> lists;

    public DocumentAdapter(Context cx, List<NotesDataClass> lists) {
        Cx = cx;
        this.lists = lists;
    }

    @NonNull

    @Override
    public DocmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Cx).inflate(R.layout.documentcard,parent,false);




        return new DocmentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocmentHolder holder, int position) {
        final NotesDataClass nclass = lists.get(position);
        holder.tv1.setText(nclass.getName());
        holder.tv2.setText(nclass.getDate());


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public  class DocmentHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;
        Button b1;
        public DocmentHolder(View itemView) {
            super(itemView);
           tv1 = itemView.findViewById(R.id.textView11);
            tv2 = itemView.findViewById(R.id.textView7);

            b1 = itemView.findViewById(R.id.button4);

        }
    }
}
