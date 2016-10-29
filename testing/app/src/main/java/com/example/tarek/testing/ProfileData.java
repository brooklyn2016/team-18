package com.example.tarek.testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileData extends AppCompatActivity {

    TextView fullName;
    TextView phoneNumber;
    TextView emailAddress;
    TextView userSince;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        bundle = getIntent().getExtras();
        fullName = (TextView) findViewById(R.id.fullName);
        phoneNumber = (TextView) findViewById(R.id.phone_number);
        emailAddress = (TextView) findViewById(R.id.email_address);
        userSince = (TextView) findViewById(R.id.userSince);
        fullName.append(bundle.getString("firstname")+" "+bundle.getString("lastname"));
        phoneNumber.append(bundle.getString("phonenumber"));
        emailAddress.append(bundle.getString("emailaddress"));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateandTime = sdf.format(new Date());
        userSince.append(currentDateandTime);
        if(bundle.getString("phonenumber").equals(""))
            phoneNumber.setVisibility(View.GONE);
    }
}
