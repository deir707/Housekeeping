package com.unipi.diodeir.house_keeping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Publish extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ToggleButton toggleButton, toggleButton2;
    TextView textView3, textView4, textView5, textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13;
    ImageButton imageButton5, imageButton6;
    ImageView imageView2;
    StorageReference storageReference;
    FirebaseAuth auth;
    DatabaseReference reference;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    Integer flag, flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, flag10, flag11, flag12;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        flag = flag1 = flag2 = flag3 = flag4 = flag5 = flag6 = flag7 = flag8 = flag9 = flag10 = flag11 = flag12 = 0;
        toggleButton = findViewById(R.id.toggleButton);
        toggleButton2 = findViewById(R.id.toggleButton2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        imageButton5 = findViewById(R.id.imageButton5);
        imageButton6 = findViewById(R.id.imageButton6);
        imageView2 = findViewById(R.id.imageView2);
        storageReference = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Listings");
        id = String.valueOf(ThreadLocalRandom.current().nextInt(0,1000000000));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        reference.child(textView3.getText().toString()+id).removeValue();
    }

    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }
    public void upload (View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent,"Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(getContentResolver(), filePath);
                imageView2.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void publish(View view) {
        if (toggleButton.isChecked() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Sale_Rent").setValue(toggleButton.getText().toString());
            flag = 1;
        }
        else if(toggleButton2.isChecked() && !textView3.getText().toString().isEmpty()){
            reference.child(textView3.getText().toString()+id).child("Sale_Rent").setValue(toggleButton2.getText().toString());
            flag = 1;
        }
        if (!textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Area").setValue(textView3.getText().toString());
            flag1 =1;
        }
        else {
            Toast.makeText(this,"Area must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView4.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Type").setValue(textView4.getText().toString());
            flag2 =1;
        }
        else {
            Toast.makeText(this,"Type must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView5.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Price").setValue(textView5.getText().toString());
            flag3 =1;
        }
        else {
            Toast.makeText(this,"Price must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView6.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Internal_Surface").setValue(textView6.getText().toString());
            flag4 =1;
        }
        else {
            Toast.makeText(this,"Internal surface must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView7.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Bedrooms").setValue(textView7.getText().toString());
            flag5 =1;
        }
        else {
            Toast.makeText(this,"Bedrooms must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView8.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Bathrooms").setValue(textView8.getText().toString());
            flag6 =1;
        }
        else {
            Toast.makeText(this,"Bathrooms must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView9.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Floor").setValue(textView9.getText().toString());
            flag7 =1;
        }
        else {
            Toast.makeText(this,"Floors must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView10.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Year_Build").setValue(textView10.getText().toString());
            flag8 =1;
        }
        else {
            Toast.makeText(this,"Year build must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView11.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Heating_Type").setValue(textView11.getText().toString());
            flag9 =1;
        }
        else {
            Toast.makeText(this,"Heating type must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView12.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Heating_Medium").setValue(textView12.getText().toString());
            flag10 =1;
        }
        else {
            Toast.makeText(this,"Heating medium must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (!textView13.getText().toString().isEmpty() && !textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("Energy_Class").setValue(textView13.getText().toString());
            flag11 =1;
        }
        else {
            Toast.makeText(this,"Energy class must be filled in",Toast.LENGTH_SHORT).show();
        }
        if (filePath != null) {
            ProgressDialog progressDialog  = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref  = storageReference.child("Listings").child(textView3.getText().toString()+id).child(textView3.getText().toString()+id);
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            reference.child(textView3.getText().toString()+id).child("Url").setValue(uri.toString());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(Publish.this,"Failed " + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0  * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage( "Uploaded " + (int)progress + "%");
                    }
                });
            flag12 = 1;
        }
        else {
            Toast.makeText(this,"A photo must be added",Toast.LENGTH_SHORT).show();
        }
        if (!textView3.getText().toString().isEmpty()) {
            reference.child(textView3.getText().toString()+id).child("ID").setValue(id);
        }
        if (flag == 1 && flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1 && flag5 == 1 && flag6 == 1 && flag7 == 1 && flag8 == 1 && flag9 == 1 && flag10 == 1 && flag11 == 1 && flag12 == 1) {
            Toast.makeText(this,"Your listing has been published!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainMenu.class);
            startActivity(intent);
        }
    }
    public void toggle1(View view) {
        if(toggleButton.isChecked()){
            toggleButton.setChecked(true);
            toggleButton2.setChecked(false);
        }
        else {
            toggleButton2.setChecked(true);
        }
    }
    public void toggle2(View view) {
        if(toggleButton2.isChecked()){
            toggleButton2.setChecked(true);
            toggleButton.setChecked(false);
        }
        else {
            toggleButton.setChecked(true);
        }
    }
    public void popup1(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.area_menu);
        popup.show();
    }
    public void popup2(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.type_menu);
        popup.show();
    }
    public void popup3(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.price_menu);
        popup.show();
    }
    public void popup4(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.internal_surface_menu);
        popup.show();
    }
    public void popup5(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.bedrooms_menu);
        popup.show();
    }
    public void popup6(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.bathrooms_menu);
        popup.show();
    }
    public void popup7(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.floors_menu);
        popup.show();
    }
    public void popup8(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.year_build_menu);
        popup.show();
    }
    public void popup9(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.heating_type_menu);
        popup.show();
    }
    public void popup10(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.heating_medium_menu);
        popup.show();
    }
    public void popup11(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.energy_class_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.area1:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area2:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area3:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area4:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area5:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area6:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area7:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area8:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area9:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area10:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area11:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area12:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area13:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area14:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area15:
                textView3.setText(item.getTitle());
                return true;
            case R.id.area16:
                textView3.setText(item.getTitle());
                return true;
            case R.id.type2:
                textView4.setText(item.getTitle());
                return true;
            case R.id.type3:
                textView4.setText(item.getTitle());
                return true;
            case R.id.type4:
                textView4.setText(item.getTitle());
                return true;
            case R.id.type5:
                textView4.setText(item.getTitle());
                return true;
            case R.id.type6:
                textView4.setText(item.getTitle());
                return true;
            case R.id.type7:
                textView4.setText(item.getTitle());
                return true;
            case R.id.price1:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price2:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price3:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price4:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price5:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price6:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price7:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price8:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price9:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price10:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price11:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price12:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price13:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price14:
                textView5.setText(item.getTitle());
                return true;
            case R.id.price15:
                textView5.setText(item.getTitle());
                return true;
            case R.id.surface1:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface2:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface3:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface4:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface5:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface6:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface7:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface8:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface9:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface10:
                textView6.setText(item.getTitle());
                return true;
            case R.id.surface11:
                textView6.setText(item.getTitle());
                return true;
            case R.id.bedroom1:
                textView7.setText(item.getTitle());
                return true;
            case R.id.bedroom2:
                textView7.setText(item.getTitle());
                return true;
            case R.id.bedroom3:
                textView7.setText(item.getTitle());
                return true;
            case R.id.bedroom4:
                textView7.setText(item.getTitle());
                return true;
            case R.id.bedroom5:
                textView7.setText(item.getTitle());
                return true;
            case R.id.bathroom1:
                textView8.setText(item.getTitle());
                return true;
            case R.id.bathroom2:
                textView8.setText(item.getTitle());
                return true;
            case R.id.bathroom3:
                textView8.setText(item.getTitle());
                return true;
            case R.id.floor2:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor3:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor4:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor5:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor6:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor7:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor8:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor9:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor10:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor11:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor12:
                textView9.setText(item.getTitle());
                return true;
            case R.id.floor13:
                textView9.setText(item.getTitle());
                return true;
            case R.id.year2:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year3:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year4:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year5:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year6:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year7:
                textView10.setText(item.getTitle());
                return true;
            case R.id.year8:
                textView10.setText(item.getTitle());
                return true;
            case R.id.heatingtype2:
                textView11.setText(item.getTitle());
                return true;
            case R.id.heatingtype3:
                textView11.setText(item.getTitle());
                return true;
            case R.id.heatingtype4:
                textView11.setText(item.getTitle());
                return true;
            case R.id.heatingmedium2:
                textView12.setText(item.getTitle());
                return true;
            case R.id.heatingmedium3:
                textView12.setText(item.getTitle());
                return true;
            case R.id.heatingmedium4:
                textView12.setText(item.getTitle());
                return true;
            case R.id.heatingmedium5:
                textView12.setText(item.getTitle());
                return true;
            case R.id.heatingmedium6:
                textView12.setText(item.getTitle());
                return true;
            case R.id.heatingmedium7:
                textView12.setText(item.getTitle());
                return true;
            case R.id.energy2:
                textView13.setText(item.getTitle());
                return true;
            case R.id.energy3:
                textView13.setText(item.getTitle());
                return true;
            case R.id.energy4:
                textView13.setText(item.getTitle());
                return true;
            case R.id.energy5:
                textView13.setText(item.getTitle());
                return true;
            case R.id.energy6:
                textView13.setText(item.getTitle());
                return true;
            case R.id.energy7:
                textView13.setText(item.getTitle());
                return true;
            default:
                return false;
        }
    }
}