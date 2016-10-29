package com.example.tarek.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class signUpPage extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.eMail);
    }

    public void goToProfile(View view){
        Intent intent = new Intent(signUpPage.this,ProfileData.class);
        intent.putExtra("firstname",firstName.getText().toString());
        intent.putExtra("lastname",lastName.getText().toString());
        intent.putExtra("phonenumber",phoneNumber.getText().toString());
        intent.putExtra("emailaddress",emailAddress.getText().toString());
        intent.putExtra("from",false);
        startActivity(intent);
    }
}
