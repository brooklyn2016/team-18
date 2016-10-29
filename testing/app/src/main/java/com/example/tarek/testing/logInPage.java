package com.example.tarek.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class logInPage extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        username = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password_enter);
    }

    public void goToUpload(View view) {
        if (username.getText().toString().equals("") || password.getText().toString().equals(""))
            Toast.makeText(this, "Some fields are empty", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, MainActivity.class);
            MainActivity.LOGGED = true;
            startActivity(intent);
        }
    }
}
