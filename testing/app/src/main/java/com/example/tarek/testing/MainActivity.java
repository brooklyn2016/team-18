package com.example.tarek.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.tarek.testing.R.id.lastName;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    EditText firstNameEdit;
    EditText lastNameEdit;
    EditText phoneNumberEdit;
    EditText emailAddressEdit;
    TextView fullName;
    TextView phoneNumber;
    TextView emailAddress;
    TextView userSince;
    Bundle bundle;
    Intent intent;
    LinearLayout dataLayout;
    LinearLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        firstNameEdit = (EditText) findViewById(R.id.firstName);
        lastNameEdit = (EditText) findViewById(lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        dataLayout = (LinearLayout) findViewById(R.id.dataLayout);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        intent = getIntent();
    }

    public void goToLogIn(View view){
        viewPager.setCurrentItem(1);
    }

    public void logIn(View view){
        Intent intent = new Intent(this,logInPage.class);
        startActivity(intent);
    }

    public void signUp(View view){
        Intent intent = new Intent(this,signUpPage.class);
        startActivity(intent);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IntroPage(),"UPLAOD");
        adapter.addFragment(new ProfilePage(),"PROFILE");
        viewPager.setAdapter(adapter);
    }

    void setUpData(){
        dataLayout.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.GONE);
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
