package com.macelitech.a5_instagramclone.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.macelitech.a5_instagramclone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; //binding to layout
    private FirebaseAuth mAuth; //firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //binding to layout
        View view = binding.getRoot(); // Returns overwrite layout to binding
        setContentView(view);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance(); //firebase authentication
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(MainActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();
        }else{

        }
    }
    public void signInClicked(View view){
        String email = binding.emailText.getText().toString(); // get text from view email text
        String password = binding.passwordText.getText().toString(); // get text from view password text
        if(email.equals("")|| password.equals("")){
            Toast.makeText(this,"Enter email and password",Toast.LENGTH_LONG).show(); // show notify
        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public void signUpClicked(View view){
        String email = binding.emailText.getText().toString(); // get text from view email text
        String password = binding.passwordText.getText().toString(); // get text from view password text
        if(email.equals("")|| password.equals("")){
            Toast.makeText(this,"Enter email and password",Toast.LENGTH_LONG).show(); // show notify
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}