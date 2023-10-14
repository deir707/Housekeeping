package com.unipi.diodeir.house_keeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MetaData extends AppCompatActivity {
    TextView textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView50, textView51, textView52, textView53, textView54, textView55, textView56;
    ImageView imageView4;
    ImageButton imageButton;
    ToggleButton toggleButton3;
    FirebaseAuth auth;
    DatabaseReference reference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data);
        textView14 = findViewById(R.id.textView14);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        textView28 = findViewById(R.id.textView28);
        textView50 = findViewById(R.id.textView50);
        textView51 = findViewById(R.id.textView51);
        textView52 = findViewById(R.id.textView52);
        textView53 = findViewById(R.id.textView53);
        textView54 = findViewById(R.id.textView54);
        textView55 = findViewById(R.id.textView55);
        textView56 = findViewById(R.id.textView56);
        imageView4 = findViewById(R.id.imageView4);
        imageButton = findViewById(R.id.imageButton9);
        toggleButton3 = findViewById(R.id.toggleButton3);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Listings");
        id = getIntent().getStringExtra("ID");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(textView22.getText().toString() + id).child("Favorite").child(Objects.requireNonNull(auth.getUid())).exists())
                {
                    toggleButton3.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (Objects.requireNonNull(auth.getCurrentUser()).isAnonymous()) {
            toggleButton3.setVisibility(View.INVISIBLE);
        }

        textView20.setText(getIntent().getStringExtra("Sale_Rent"));
        textView22.setText(getIntent().getStringExtra("Area"));
        textView23.setText(getIntent().getStringExtra("Price"));
        textView26.setText(getIntent().getStringExtra("Type"));
        textView27.setText(getIntent().getStringExtra("Internal_Surface"));
        textView25.setText(getIntent().getStringExtra("Bedrooms"));
        textView24.setText(getIntent().getStringExtra("Bathrooms"));
        textView55.setText(getIntent().getStringExtra("Floor"));
        textView56.setText(getIntent().getStringExtra("Year_Build"));
        textView51.setText(getIntent().getStringExtra("Heating_Type"));
        textView54.setText(getIntent().getStringExtra("Energy_Class"));
        textView28.setText(getIntent().getStringExtra("Heating_Medium"));
        Picasso.get().load(getIntent().getStringExtra("Url")).fit().centerCrop().into(imageView4);
    }

    public void onToggleClick(View view) {
        if (toggleButton3.isChecked()) {
            reference.child(textView22.getText().toString() + id).child("Favorite").child(Objects.requireNonNull(auth.getUid())).setValue("True");
            toggleButton3.setChecked(true);
            Toast.makeText(this,"You have added this listing to your favorites!",Toast.LENGTH_SHORT).show();
        }
        else {
            reference.child(textView22.getText().toString() + id).child("Favorite").child(Objects.requireNonNull(auth.getUid())).removeValue();
            toggleButton3.setChecked(false);
            Toast.makeText(this,"You have removed this listing from your favorites!",Toast.LENGTH_SHORT).show();
        }
    }
    public void call(View view) {
        Toast.makeText(this,"Your call is being diverted",Toast.LENGTH_SHORT).show();
    }
}