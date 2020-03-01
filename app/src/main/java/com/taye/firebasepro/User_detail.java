package com.taye.firebasepro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class User_detail extends AppCompatActivity {

    public TextView nameAdap, emailadap, phone, username, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_item);
        nameAdap = findViewById(R.id.fullname);
        emailadap = findViewById(R.id.user_name);
        emailadap = findViewById(R.id.emailholdr);
        gender = findViewById(R.id.sex);
        Intent i = getIntent();
        nameAdap.setText(i.getStringExtra("fullname"));
        username.setText(i.getStringExtra("username"));
        emailadap.setText(i.getStringExtra("email"));
        gender.setText(i.getStringExtra("sex"));

    }
}
