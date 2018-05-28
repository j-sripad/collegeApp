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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

public class Admin extends AppCompatActivity {
    EditText etext;
    Button b1,b2,b3,b4;
    ImageView imageView;
    DatabaseReference dref;
    StorageReference sref;
    private Uri uri;
    final static int PICK_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        etext = findViewById(R.id.editText);
        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);
        b3 = findViewById(R.id.bb);

        dref = FirebaseDatabase.getInstance().getReference("Notices");
        sref = FirebaseStorage.getInstance().getReference("Notices");
        imageView = findViewById(R.id.imageView9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadData();
        }
    });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this,Documentupdate.class);
                startActivity(i);

            }
        });




    }



        public void showData()
    {
        Intent intent =  new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{if(requestCode == PICK_CODE&&resultCode == RESULT_OK&&data!=null && data.getData()!=null)
        {
            uri = data.getData();
           // Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show();

            Glide.with(this).load(uri).fitCenter().into(imageView);
        }
        else
        {
            Toast.makeText(this,"NOIMAGE",Toast.LENGTH_SHORT).show();
        }

    }catch(Exception e){System.out.println(e);}}

    public String getType(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void uploadData()
    {if(uri!=null){
        StorageReference fileref = sref.child(System.currentTimeMillis()+"."+getType(uri));
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Admin.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                Date currentTime = Calendar.getInstance().getTime();
                NoticeData ndata = new NoticeData(etext.getText().toString().trim(), currentTime.toString(), taskSnapshot.getDownloadUrl().toString());
                String k = dref.push().getKey();
                dref.child(k).setValue(ndata);



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin.this,"Failed",Toast.LENGTH_SHORT).show();

            }
        });
    }}





}

