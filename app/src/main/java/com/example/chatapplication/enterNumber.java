package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class enterNumber extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText pnumber;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        SharedPreferences sharedPreferences=getSharedPreferences("auth",MODE_PRIVATE);

        pnumber = findViewById(R.id.phonenumber_id);
        System.out.println(pnumber.getText().toString());
        button = findViewById(R.id.nextbutton_id);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = pnumber.getText().toString();
                ccp.registerCarrierNumberEditText(pnumber);
               Intent i = new Intent(enterNumber.this,enterOTP.class);
                i.putExtra("Mobile",ccp.getFullNumberWithPlus().replace(" ",""));

               if (number.isEmpty()){
                   Toast.makeText(enterNumber.this, "Enter a number", Toast.LENGTH_SHORT).show();
               }else if (number.length()!=10){
                   pnumber.setError("Enter 10 Digit number");
               }else {
                   SharedPreferences.Editor editor=sharedPreferences.edit();
                   editor.putBoolean("flag",true);
                   editor.apply();
                   startActivity(i);
               }
            }
        });

    }
}