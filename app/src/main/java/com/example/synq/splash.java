package com.example.synq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
    Button loginRef;
    TextView SignUpTXT;
    FirebaseAuth auth; //check data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth.getCurrentUser() != null){         //if user is not logged in, Go to Login Frame
                    Intent intent = new Intent(splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(splash.this,login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}