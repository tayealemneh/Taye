package com.taye.usersSession.recyclerview;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.taye.usersSession.R;

import java.util.ArrayList;
public class ListUsers extends RecyclerView.Adapter<ListUsers.userHolder> {
        ArrayList<com.taye.usersSession.recyclerview.users> users;
        DatabaseReference databaseReference;
        public ListUsers(ArrayList<users> users, DatabaseReference myRef){
            this.users=users;
            this.databaseReference=myRef;
        }

        @NonNull
        @Override
        public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.users_item, parent, false);
            return new userHolder(listItem);
        }

        @Override
        public void onBindViewHolder(@NonNull userHolder holder, int pp) {
            final int p=pp;
            final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            holder.fullName.setText(users.get(pp).getFullName());
            holder.username.setText(users.get(pp).getUserName());
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final View vv=v;
                    PopupMenu pum=new PopupMenu(v.getContext(),v);

                    pum.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            //TODO deleting the user from fire base
                            databaseReference.child(currentuser).removeValue();
                            Toast.makeText(vv.getContext(), "student deleted successfully", Toast.LENGTH_SHORT).show();
                            users.remove(users.get(p));
                         ListUsers.this.notifyDataSetChanged();
                            return true;
                        }
                    });
                    pum.show();
                    return false;
                }
            });
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), User_detail.class);
                    intent.putExtra("Fullname",users.get(p).getFullName());
                    intent.putExtra("username",users.get(p).getUserName());
                    intent.putExtra("email",users.get(p).getEmail());
                    intent.putExtra("sex",users.get(p).getSex());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if(users == null)
                return 0;
            return users.size();
        }

        public  class userHolder extends RecyclerView.ViewHolder{
            TextView fullName,username;
            CardView cardView;

            public userHolder(@NonNull View itemView) {
                super(itemView);
                fullName=itemView.findViewById(R.id.fullname);
                username=itemView.findViewById(R.id.emailholdr);
                cardView=itemView.findViewById(R.id.cardView);
            }
        }
    }




