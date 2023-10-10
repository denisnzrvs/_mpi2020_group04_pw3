package com.example.repositoryg_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the selected theme from SharedPreferences or use the default theme
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String selectedTheme = sharedPref.getString("selected_theme", "Default");

        // Set the app's theme based on the selected theme, ignoring system settings ("Default" dropdown option does follow system settings)
        if (selectedTheme.equals("Light theme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (selectedTheme.equals("Dark theme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        setContentView(R.layout.activity_main);

        //Get the previously input text by user, but it into the input field
        EditText textInputField = findViewById(R.id.textInputEditText);
        String savedText = sharedPref.getString("user_input", "");
        textInputField.setText(savedText);

        //Get dropdown, buttons
        Spinner spinner = findViewById(R.id.themesSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.themes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button act2Btn = findViewById(R.id.act2Button);

        //Create event listener hat takes user to MainActivity2
        act2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        Button saveBtn = findViewById(R.id.saveButton);

        //Create event listener that saves the input text field's text into Shared Preferences
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = String.valueOf(textInputField.getText());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("user_input", userInput);
                editor.apply();
            }
        });

        //Store the selected dropdown item in Shared Preferences, immediately set the theme without needing to re-render the Activity
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedTheme = spinner.getSelectedItem().toString();

                // Save the selected theme in SharedPreferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("selected_theme", selectedTheme);
                editor.apply();

                // Change the app's theme, ignoring system settings
                if (selectedTheme.equals("Dark theme")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else if (selectedTheme.equals("Light theme")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });
    }
}
