package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Messages extends AppCompatActivity {

    BottomNavigationView botoomview;
    Toolbar toolbar;
    Fragment fragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_main_page);
        SharedPreferences pre = getSharedPreferences("auth",MODE_PRIVATE);
        SharedPreferences.Editor editor= pre.edit();
        editor.putBoolean("flag",true);
        editor.apply();
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);


        botoomview = findViewById(R.id.bottmnavigation_id);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Msg_home_page()).commit();


        botoomview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                switch (item.getItemId()) {
                    case R.id.message_id:
                        fragment = new Msg_home_page();
                        break;
                    case R.id.contacts_id:
                        fragment = new contacts();
                        break;

                    case R.id.call_id:
                        fragment = new Call();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_id:
                startActivity(new Intent(Messages.this,setting.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}