package com.example.pr_w3;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;
    private Button goToSecondButton;
    private Spinner themeSpinner;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);
        goToSecondButton = findViewById(R.id.goToSecondButton);
        themeSpinner = findViewById(R.id.themeSpinner);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Populate the Spinner with items
        String[] themes = {"Default", "Dark theme", "Light theme"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, themes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(adapter);

        // Check if there is a saved value and display it in the EditText
        String savedValue = sharedPreferences.getString("savedValue", "");
        editText.setText(savedValue);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSave = editText.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("savedValue", textToSave);
                editor.apply();
            }
        });

        goToSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch SecondActivity
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedTheme = themes[position];
                if (selectedTheme.equals("Dark theme")) {
                    // Change to dark theme (you can implement theme changes here)
                } else if (selectedTheme.equals("Light theme")) {
                    // Change to light theme (you can implement theme changes here)
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }
}