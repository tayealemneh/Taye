package com.taye.usersSession.recyclerview;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taye.usersSession.R;

import java.util.ArrayList;
public class MainActivity2 extends AppCompatActivity {
Toolbar toolbar;
FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ListUsers listUsers;
    ArrayList<users> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        //toolbar=findViewById(R.id.toolBar);
        //setSupportActionBar(toolbar);
        list=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("students");
        recyclerView=findViewById(R.id.listrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listUsers=new ListUsers(list,databaseReference);
        recyclerView.setAdapter(listUsers);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                users ss=dataSnapshot.getValue(users.class);
                list.add(ss);
                listUsers.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                users ss=dataSnapshot.getValue(users.class);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
       // menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        firebaseAuth.signOut();
        Intent i=new Intent(MainActivity2.this, MainActivity2.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
