package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class enterOTP extends AppCompatActivity {
    EditText OTP;
    Button nextbtn;
    String phonenumber;
    String otpid;
    TextView codeagain;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        phonenumber = getIntent().getStringExtra("Mobile").toString();
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        nextbtn = findViewById(R.id.nextbutton1_id);
        OTP = findViewById(R.id.entercode);
        codeagain = findViewById(R.id.sendocde_id);
        System.out.println(phonenumber);
        initiateotp();




        codeagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nextbtn.setOnClickListener(view -> {
            String code = OTP.getText().toString();

            if (OTP.getText().toString().isEmpty())
                Toast.makeText(getApplicationContext(), "Blank Field can not be processed", Toast.LENGTH_LONG).show();
            else if (OTP.getText().toString().length() != 6)
                Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_LONG).show();
            else {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, OTP.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }



        });

    }

    private void initiateotp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });        // OnVerificationStateChangedCallbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");


        String name = getIntent().getStringExtra("name");


        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String Userid = firebaseAuth.getUid();
                            Toast.makeText(enterOTP.this, "Data Added", Toast.LENGTH_SHORT).show();
                            UserModel userModel = new UserModel(name,phonenumber,Userid);
                            databaseReference.child(Userid).setValue(userModel);
                            startActivity(new Intent(enterOTP.this, Messages.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Signin Code Error", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

}