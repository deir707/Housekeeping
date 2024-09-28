package com.unipi.diodeir.house_keeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Search extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton imageButton;
    ToggleButton toggleButton4, toggleButton5;
    TextView textView30;
    DatabaseReference database;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<ListItems> list;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textView30 = findViewById(R.id.textView30);
        toggleButton4 = findViewById(R.id.toggleButton4);
        toggleButton5 = findViewById(R.id.toggleButton5);
        imageButton = findViewById(R.id.imageButton7);
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.listings_View);
        database = FirebaseDatabase.getInstance().getReference("Listings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            imageButton.setVisibility(View.INVISIBLE);
        }
    }
    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }
    public void toggle4(View view) {
        if(toggleButton4.isChecked()){
            toggleButton4.setChecked(true);
            toggleButton5.setChecked(false);
        }
        else {
            toggleButton5.setChecked(true);
        }
    }
    public void toggle5(View view) {
        if(toggleButton5.isChecked()){
            toggleButton5.setChecked(true);
            toggleButton4.setChecked(false);
        }
        else {
            toggleButton4.setChecked(true);
        }
    }
    public void popup12(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.area_menu);
        popup.show();
    }
    public void search(View view) {
        list.clear();
        if (toggleButton4.isChecked() && textView30.getText().toString().isEmpty()){
            database.orderByChild("Sale_Rent").equalTo("SALE").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ListItems listItems = dataSnapshot.getValue(ListItems.class);
                        list.add(listItems);
                    }
                    myAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else if(toggleButton5.isChecked() && textView30.getText().toString().isEmpty()){
            database.orderByChild("Sale_Rent").equalTo("RENT").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ListItems listItems = dataSnapshot.getValue(ListItems.class);
                        list.add(listItems);
                    }
                    myAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(toggleButton4.isChecked() && !textView30.getText().toString().isEmpty()) {
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ListItems listItems = dataSnapshot.getValue(ListItems.class);
                            if(Objects.requireNonNull(dataSnapshot.child("Area").getValue()).toString().equals(textView30.getText().toString()) && Objects.requireNonNull(dataSnapshot.child("Sale_Rent").getValue()).toString().equals("SALE")){
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
        else if(toggleButton5.isChecked() && !textView30.getText().toString().isEmpty()) {
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ListItems listItems = dataSnapshot.getValue(ListItems.class);
                        if(Objects.requireNonNull(dataSnapshot.child("Area").getValue()).toString().equals(textView30.getText().toString()) && Objects.requireNonNull(dataSnapshot.child("Sale_Rent").getValue()).toString().equals("RENT")){
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
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.area1:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area2:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area3:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area4:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area5:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area6:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area7:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area8:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area9:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area10:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area11:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area12:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area13:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area14:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area15:
                textView30.setText(item.getTitle());
                return true;
            case R.id.area16:
                textView30.setText(item.getTitle());
                return true;
            default:
                return false;
        }
    }
}