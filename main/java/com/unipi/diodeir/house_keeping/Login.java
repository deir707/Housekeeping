package com.unipi.diodeir.house_keeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText usernameField, passwordField;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = findViewById(R.id.editTextTextEmailAddress);
        passwordField = findViewById(R.id.editTextTextPassword);
        auth = FirebaseAuth.getInstance();
    }
    public void signin(View view) {
        if (usernameField.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty()) {
            Toast.makeText(this,"Username and password are required!",Toast.LENGTH_SHORT).show();
        }
        else {
            auth.signInWithEmailAndPassword(usernameField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        success();
                    } else {
                        showMessage("Error", task.getException().getLocalizedMessage());
                    }
                }
            });
        }
    }
    public void signup(View view) {
        if (usernameField.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty()) {
            Toast.makeText(this, "Username and password are required!", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(usernameField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showMessage("Success", "User created! \n\nTap to sign in to enter");
                            } else {
                                showMessage("Error", task.getException().getLocalizedMessage());
                            }
                        }
            });
        }
    }
    void showMessage(String title, String message){
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(true).show();
    }
    void success() {
        Toast.makeText(this,"Successful sign in!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
}