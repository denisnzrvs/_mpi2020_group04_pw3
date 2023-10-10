package com.example.pr_w3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView displayText;
    private Button readPreferencesButton;
    private Button backButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        displayText = findViewById(R.id.displayText);
        readPreferencesButton = findViewById(R.id.readPreferencesButton);
        backButton = findViewById(R.id.backButton);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        readPreferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedValue = sharedPreferences.getString("savedValue", "");
                if (!savedValue.isEmpty()) {
                    displayText.setText(savedValue);
                } else {
                    Toast.makeText(SecondActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to MainActivity and clear the backstack
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
