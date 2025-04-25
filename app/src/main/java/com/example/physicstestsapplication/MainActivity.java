package com.example.physicstestsapplication;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressManager progressManager;
    private TextView gemsCounter;
    private TextView streakCounter;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressManager = new ProgressManager(this);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        gemsCounter = findViewById(R.id.gemsCounter);
        streakCounter = findViewById(R.id.streakCounter);
        Button profileButton = findViewById(R.id.buttonProfile);
        String username = getIntent().getStringExtra("USERNAME");
        welcomeTextView.setText(username != null ?
                "Добро пожаловать, " + username + "!" : "Добро пожаловать!");
        updateProgressUI();
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        });
        createTestButtons();
    }

    private void createTestButtons() {
        LinearLayout buttonContainer = findViewById(R.id.button_container);

        for (int i = 1; i <= 10; i++) {
            Button testButton = new Button(this);
            testButton.setText("Тест " + i);
            testButton.setBackgroundResource(R.drawable.ic_launcher_background);
            testButton.getBackground().setColorFilter(
                    Color.parseColor("#6200EE"),
                    PorterDuff.Mode.SRC_IN
            );
            testButton.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16);
            testButton.setLayoutParams(params);
            final int testNumber = i;
            testButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("TEST_NUMBER", testNumber);
                startActivity(intent);
            });

            buttonContainer.addView(testButton);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgressUI();
    }

    private void updateProgressUI() {
        if (progressManager != null) {
            progressManager.updateStreak();
            gemsCounter.setText("💎 " + progressManager.getGems());
            streakCounter.setText("🔥 " + progressManager.getStreak() + " дн.");
        }
    }
}