package com.unipi.diodeir.house_keeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Favorites extends AppCompatActivity {
    ImageButton imageButton;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ListItems> list;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        imageButton = findViewById(R.id.imageButton8);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.favoritesList);
        database = FirebaseDatabase.getInstance().getReference("Listings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            imageButton.setVisibility(View.INVISIBLE);
        }

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ListItems listItems = dataSnapshot.getValue(ListItems.class);
                    if(Objects.requireNonNull(dataSnapshot.child("Favorite").child(Objects.requireNonNull(auth.getUid()))).exists()){
                        list.add(listItems);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ListItems listItems = dataSnapshot.getValue(ListItems.class);
                    if(Objects.requireNonNull(dataSnapshot.child("Favorite").child(Objects.requireNonNull(auth.getUid()))).exists()){
                        list.add(listItems);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }
}