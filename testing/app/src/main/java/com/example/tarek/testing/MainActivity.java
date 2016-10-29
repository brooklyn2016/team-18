package com.example.tarek.testing;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.View.GONE;
import static com.example.tarek.testing.R.id.lastName;

public class MainActivity extends AppCompatActivity {
    public static boolean LOGGED=false;
    private static final int SELECT_PHOTO = 100;
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
        if(LOGGED && dataLayout!=null && loginLayout!=null) {
            dataLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(GONE);
        }
    }

    public void goToLogIn(View view){
        if(!LOGGED)
            viewPager.setCurrentItem(1);
        else
            uploadVideo(view);
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
        adapter.addFragment(new IntroPage(),"UPLOAD");
        adapter.addFragment(new ProfilePage(),"PROFILE");
        viewPager.setAdapter(adapter);
    }

    void setUpData(){
        dataLayout.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(GONE);
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
            phoneNumber.setVisibility(GONE);
    }

    public void uploadVideo(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        if (resultCode != RESULT_CANCELED && imageReturnedIntent!=null) {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            switch (requestCode) {
                case SELECT_PHOTO:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(
                                selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();


                        Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    }
            }
        }
    }
}
