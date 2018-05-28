package com.example.sripadpc.collegeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {
    DatabaseReference dataref;
    List<NotesDataClass> Filedata;
    RecyclerView recyclerView;
    DocumentAdapter dA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        dataref = FirebaseDatabase.getInstance().getReference("Notes");
        recyclerView = findViewById(R.id.Documentrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Filedata = new ArrayList();
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ps : dataSnapshot.getChildren())
                {
                    NotesDataClass nclass = ps.getValue(NotesDataClass.class);
                    Filedata.add(nclass);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            dA = new DocumentAdapter(Notes.this,Filedata);
            recyclerView.setAdapter(dA);
    }
}
