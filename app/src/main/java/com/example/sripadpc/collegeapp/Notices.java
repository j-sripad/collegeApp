package com.example.sripadpc.collegeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Notices extends AppCompatActivity {
    DatabaseReference dref;
    List<NoticeData> Listt;
    ImageAdapter imageAdapter;
    //StorageReference  stref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        dref = FirebaseDatabase.getInstance().getReference("Notices");
        //stref = FirebaseStorage.getInstance().getReference("Notices");
        final RecyclerView rview = findViewById(R.id.RecycleId);
        rview.setLayoutManager(new LinearLayoutManager(this));
        Listt = new ArrayList();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ps : dataSnapshot.getChildren())
                {
                    NoticeData ndata = ps.getValue(NoticeData.class);
                    Listt.add(ndata);
                }
                imageAdapter = new ImageAdapter(Notices.this,Listt);
                rview.setAdapter(imageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Notices.this,"OOPS",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
