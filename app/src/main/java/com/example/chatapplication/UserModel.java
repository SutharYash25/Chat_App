package com.example.chatapplication;

import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserModel {

    private  String Username,pnumber,Userid;

    public UserModel(String username, String pnumber, String userid) {
        Username = username;
        this.pnumber = pnumber;
        Userid = userid;
    }

    public UserModel() {
    }

    public UserModel(TextInputLayout editName, TextInputLayout editEmailid, String uid, String toString) {
    }

    public UserModel(TextView editprofile, TextView emailid, String uid, String toString) {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }
}
