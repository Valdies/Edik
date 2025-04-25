package com.example.physicstestsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProgressManager progressManager = new ProgressManager(this);

        TextView usernameView = findViewById(R.id.textViewUsername);
        TextView gemsView = findViewById(R.id.gemsStat);
        TextView streakView = findViewById(R.id.streakStat);
        Button backButton = findViewById(R.id.buttonBackToTests);
        String username = getIntent().getStringExtra("USERNAME");
        usernameView.setText(username != null ?
                "Пользователь: " + username : "Гость");

        gemsView.setText("Гемы: " + progressManager.getGems());
        streakView.setText("Серия: " + progressManager.getStreak() + " дней");

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
        });
    }
}