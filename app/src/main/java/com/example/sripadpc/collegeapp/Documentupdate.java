package com.example.sripadpc.collegeapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

public class Documentupdate extends AppCompatActivity {
    Button b1, b2;
    EditText ettx;
    private static final int PICK = 111;
    Uri urii;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentupdate);
        b1 = findViewById(R.id.selectButton);
        b2 = findViewById(R.id.bb);
        ettx = findViewById(R.id.Txrwin);
        storageReference = FirebaseStorage.getInstance().getReference("Notes");
        databaseReference = FirebaseDatabase.getInstance().getReference("Notes");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatata();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

    }

    public void showDatata() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK && resultCode == RESULT_OK && data != null && data.getData() != null) {
            urii = data.getData();
        } else {
            Toast.makeText(Documentupdate.this, "oops", Toast.LENGTH_SHORT).show();
        }
    }


    public String getTypenotes(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void uploadFile() {
        if (urii != null) {
            StorageReference ffref = storageReference.child(System.currentTimeMillis() + "." + getTypenotes(urii));
            ffref.putFile(urii).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Date currentTime = Calendar.getInstance().getTime();
                    NotesDataClass nc = new NotesDataClass(ettx.getText().toString().trim(),currentTime.toString(), taskSnapshot.getDownloadUrl().toString());
                    Toast.makeText(Documentupdate.this, "Success", Toast.LENGTH_SHORT).show();
                    String key = databaseReference.push().getKey();
                    databaseReference.child(key).setValue(nc);
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Documentupdate.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
