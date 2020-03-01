package com.taye.firebasepro;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email2, password2;
    ProgressBar progressBar2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email2 = (EditText) findViewById(R.id.email2);
        password2= (EditText) findViewById(R.id.password2);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, sign_out.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar2.setVisibility(View.GONE);
    }

    public void loginButtonClicked(View view) {
        String Email = email2.getText().toString();
        final String pass = password2.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar2.setVisibility(View.VISIBLE);

        if (password2.length() < 6) {
            password2.setError("Should be greater than 6");
        }
        //authenticate user
        auth.signInWithEmailAndPassword(Email, pass)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(login.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_LONG).show();
                            Log.e("MyTag", task.getException().toString());

                        } else {
                            Intent intent = new Intent(login.this, sign_out.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}

