package com.example.chatapplication;



import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class setting extends AppCompatActivity {


    TextView editprofile,editphonenumber;
    CircleImageView profilephoto;
    TextView changeprofile;
    Button save;
    Uri uri;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        editprofile = findViewById(R.id.edite_profile_id);
        profilephoto = findViewById(R.id.profile_photo_id);
        logout = findViewById(R.id.log_out_id);
        editphonenumber = findViewById(R.id.edit_phonenumber_id);


         profilephoto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(setting.this, "pick image", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_PICK);
                 startActivityForResult(intent,1);
             }
         });






        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(setting.this,edit_name_profile.class);
                startActivity(i);
            }
        });


        editphonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(setting.this,edit_phone_number.class);
                startActivity(i);
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("flag", false);
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(setting.this, enterNumber.class));
                finish();
            }
        });

    }

    private void uploadTofirebase() {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference("image1"+new Random().nextInt(15));
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("User");
                        String uid=databaseReference.push().getKey();
                        UserModel userModel=new UserModel(editprofile,editphonenumber,uid,uri.toString());
                        databaseReference.child(uid).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(setting.this, "adding success", Toast.LENGTH_SHORT).show();
                                    editphonenumber.setText(null);
                                    editprofile.setText(null);
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(setting.this, "fail to add data", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.getMessage());
                        progressDialog.dismiss();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(e.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK)
        {
            uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                profilephoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }




}











