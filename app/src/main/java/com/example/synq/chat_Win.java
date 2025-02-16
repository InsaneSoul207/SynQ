//package com.example.synq;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//import java.util.Date;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class chat_Win extends AppCompatActivity {
//
//    String reciverimg, reciverName, reciverUid, SenderUID;
//    CircleImageView profile;
//    TextView reciverNName;
//    CardView sendbtn;
//    EditText textmsg;
//    FirebaseAuth firebaseAuth;
//    FirebaseDatabase database;
//    public static String senderImg;
//    public static String reciverIImg;
//    String senderRoom, reciverRoom;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_chat_win);
//
//        reciverName = getIntent().getStringExtra("nameeee");
//        reciverimg = getIntent().getStringExtra("reciverImg");
//        reciverUid = getIntent().getStringExtra("uid");
//
//        sendbtn = findViewById(R.id.sendbtnn);
//        textmsg = findViewById(R.id.textmsg);
//        profile = findViewById(R.id.profileimgg);
//        reciverNName = findViewById(R.id.recivername);
//
//        reciverNName.setText(""+reciverName);
//        Picasso.get().load(reciverimg).into(profile);
//
//        DatabaseReference reference = database.getReference().child("users").child(firebaseAuth.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                senderImg = snapshot.child("profilepic").getValue().toString();
//                reciverIImg = reciverimg;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        SenderUID = firebaseAuth.getUid();
//        senderRoom = SenderUID + reciverUid;
//        reciverRoom = reciverUid + SenderUID;
//
//        sendbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String message = textmsg.getText().toString();
//                if(message.isEmpty()){
//                    Toast.makeText(chat_Win.this, "Enter The Message First...", Toast.LENGTH_SHORT).show();
//                }
//                textmsg.setText("");
//                Date date = new Date();
//                msgModelclass messagesss = new msgModelclass(message, SenderUID, date.getTime());
//                database = FirebaseDatabase.getInstance();
//                database.getReference().child("chats").child("senderRoom").child("messages").push().setValue(messagesss).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        database.getReference().child("chats").child("reciverRoom").child("messages").push().setValue(messagesss).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                            }
//                        });
//                    }
//                });
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}
package com.example.synq;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_Win extends AppCompatActivity {

    String reciverimg, reciverName, reciverUid, SenderUID;
    CircleImageView profile;
    TextView reciverNName;
    CardView sendbtn;
    EditText textmsg;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    public static String senderImg;
    public static String reciverIImg;
    String senderRoom, reciverRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_win);

        // Initialize Firebase instances
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Get data from intent
        reciverName = getIntent().getStringExtra("nameeee");
        reciverimg = getIntent().getStringExtra("reciverImg");
        reciverUid = getIntent().getStringExtra("uid");

        // Initialize views
        sendbtn = findViewById(R.id.sendbtnn);
        textmsg = findViewById(R.id.textmsg);
        profile = findViewById(R.id.profileimgg);
        reciverNName = findViewById(R.id.recivername);

        // Set receiver's name and image
        reciverNName.setText(reciverName);
        Picasso.get().load(reciverimg).into(profile);

        // Get sender UID
        SenderUID = firebaseAuth.getCurrentUser().getUid();

        // Create chat rooms
        senderRoom = SenderUID + reciverUid;
        reciverRoom = reciverUid + SenderUID;

        // Reference to fetch sender profile pic
        DatabaseReference reference = database.getReference().child("users").child(SenderUID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.child("profilepic").getValue() != null) {
                    senderImg = snapshot.child("profilepic").getValue().toString();
                    reciverIImg = reciverimg;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(chat_Win.this, "Failed to load profile picture", Toast.LENGTH_SHORT).show();
            }
        });

        // Send button click listener
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = textmsg.getText().toString().trim();
                if (message.isEmpty()) {
                    Toast.makeText(chat_Win.this, "Enter the message first...", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Clear input field
                textmsg.setText("");

                // Create message object
                Date date = new Date();
                msgModelclass messagesss = new msgModelclass(message, SenderUID, date.getTime());

                // Push message to sender room
                database.getReference().child("chats").child(senderRoom).child("messages")
                        .push().setValue(messagesss).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Push message to receiver room
                                    database.getReference().child("chats").child(reciverRoom).child("messages")
                                            .push().setValue(messagesss);
                                } else {
                                    Toast.makeText(chat_Win.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
