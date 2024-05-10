package com.example.javalaba4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegistrationActivity extends AppCompatActivity {


    EditText loginEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginEditText = findViewById(R.id.editTextLogin);
        emailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        repeatPasswordEditText = findViewById(R.id.editTextPasswordRepeat);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (checkFields()) {
                writeToFile(String.valueOf(loginEditText.getText()), this, "login");
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    protected boolean checkFields() {
        if (loginEditText.length() == 0 && emailEditText.length() == 0) {
            loginEditText.setError("Input Login");
            emailEditText.setError("Input Email");
            return false;
        } else if (loginEditText.length() == 0) {
            loginEditText.setError("Input Login");
            return false;
        } else if (emailEditText.length() == 0) {
            emailEditText.setError("Input Email");
            return false;
        }
        if (passwordEditText.length() == 0) {
            passwordEditText.setError("Input Password");
            return false;
        }
        if (repeatPasswordEditText.length() == 0) {
            repeatPasswordEditText.setError("Repeat Password");
            return false;
        }
        if (passwordEditText.length() < 8) {
            passwordEditText.setError("Enter a password longer than 8 characters");
            return false;
        }
        if (!String.valueOf(repeatPasswordEditText.getText()).equals(String.valueOf(passwordEditText.getText()))) {
            passwordEditText.setError("Passwords are not equal");
            repeatPasswordEditText.setError("Passwords are not equal");
            return false;
        }
        return true;
    }

    private void writeToFile(String data, Context context, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName + ".txt", MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exeption", "File write failed " + e);
        }
    }
}