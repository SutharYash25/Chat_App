package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatapplogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pre = getSharedPreferences("auth",MODE_PRIVATE);
                Intent intent;
                boolean check= pre.getBoolean("flag",false);

                if (check)
                {       //for true (user login in)
                    Toast.makeText(MainActivity.this, "user", Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this,Messages.class);
                }

                else
                {   //for false (user first time loigin )
                    Toast.makeText(MainActivity.this, "first time login", Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this, enterNumber.class);
                }

                startActivity(intent);
//                startActivity(new Intent(MainActivity.this,verify.class));
                finish();
            }
        },3000);
    }
}