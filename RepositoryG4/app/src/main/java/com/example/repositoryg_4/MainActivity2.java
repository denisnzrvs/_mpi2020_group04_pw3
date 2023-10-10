package com.example.repositoryg_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button readPrefsBtn = findViewById(R.id.readPrefsButton);
        readPrefsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String selectedTheme = sharedPref.getString("selected_theme", "Default");
                String savedText = sharedPref.getString("user_input", "");
                EditText textField = findViewById(R.id.textInputEditText);

                if (savedText == null || savedText.equals("") && (selectedTheme == null || selectedTheme.equals(""))) { // Use .equals() for string comparison
                    String text = "Nothing is saved in Shared Preferences!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity2.this, text, duration);
                    toast.show();
                } else if (savedText == null || savedText.equals("")) { // Use .equals() for string comparison
                    String text = "Only a theme setting found in Shared Preferences!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(MainActivity2.this, text, duration);
                    toast.show();
                    String textToShow = selectedTheme;
                    textField.setText(textToShow);
                }
                else if (savedText != null && !savedText.equals("")) {
                    String textToShow = selectedTheme + "; " + savedText;
                    textField.setText(textToShow);



            }}});
            Button backBtn = findViewById(R.id.backButton);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goBack = new Intent(MainActivity2.this, MainActivity.class);
                    goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(goBack);
                }
            });


    }
}