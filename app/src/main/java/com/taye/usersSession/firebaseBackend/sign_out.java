package com.taye.usersSession.firebaseBackend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.taye.usersSession.recyclerview.MainActivity2;
import com.taye.usersSession.R;

public class sign_out extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
    }
    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity2.class));
    }
}

