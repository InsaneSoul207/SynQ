package com.example.synq;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

public class ActivityProfile extends AppCompatActivity {

    MaterialCardView usernameCard, emailCard, buttonCard;
    ShapeableImageView profileImageView;
    Button cardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Make sure your XML is named activity_profile.xml

        // View Bindings
        usernameCard = findViewById(R.id.usernameCard);
        emailCard = findViewById(R.id.emailCard);
        buttonCard = findViewById(R.id.buttonCard);
        profileImageView = findViewById(R.id.profileImageView);
        cardButton = findViewById(R.id.cardButton);

        // Button Click
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityProfile.this, "Edit Profile clicked!", Toast.LENGTH_SHORT).show();

                // Optionally navigate to EditProfile activity
                // Intent intent = new Intent(ActivityProfile.this, EditProfileActivity.class);
                // startActivity(intent);
            }
        });
    }
}