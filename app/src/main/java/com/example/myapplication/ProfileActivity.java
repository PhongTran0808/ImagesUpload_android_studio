package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.User;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {

    TextView tvId, tvUsername, tvFullName, tvEmail, tvGender;
    ImageView imgAvatar;
    Button btnLogout;
    DatabaseHelper db;
    String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DatabaseHelper(this);
        
        if (getIntent().hasExtra("USERNAME")) {
            currentUsername = getIntent().getStringExtra("USERNAME");
        }

        tvId = findViewById(R.id.tvId);
        tvUsername = findViewById(R.id.tvUsername);
        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnLogout = findViewById(R.id.btnLogout);

        loadUserData();

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", currentUsername);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        if (currentUsername == null) return;

        User user = db.getUser(currentUsername);
        if (user != null) {
            tvId.setText(String.valueOf(user.getId()));
            tvUsername.setText(user.getUsername());
            tvFullName.setText(user.getFullName());
            tvEmail.setText(user.getEmail());
            tvGender.setText(user.getGender());

            if (user.getAvatar() != null) {
                // Use Glide to load image from file path
                Glide.with(this)
                        .load(new File(user.getAvatar()))
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(imgAvatar);
            }
        }
    }
}
