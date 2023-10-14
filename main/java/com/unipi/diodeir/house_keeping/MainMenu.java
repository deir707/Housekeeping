package com.unipi.diodeir.house_keeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainMenu extends AppCompatActivity {
    TextView textView2;
    ImageButton imageButton, imageButton2, imageButton3, imageButton4;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        textView2 = findViewById(R.id.textView2);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        auth = FirebaseAuth.getInstance();

        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            imageButton.setVisibility(View.INVISIBLE);
        }
    }
    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }
    public void go3 (View view) {
        Intent intent = new Intent(this,Search.class);
        startActivity(intent);
    }
    public void go4 (View view) {
        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Publish.class);
            startActivity(intent);
        }
    }
    public void go5 (View view) {
        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Favorites.class);
            startActivity(intent);
        }
    }
}