package com.mango.ammad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginPage extends AppCompatActivity {


    EditText user,pass;
    UserData userInfo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        user= findViewById(R.id.editSignEmail);
        pass= findViewById(R.id.editSignPassword);
        dbHelperClass db= new dbHelperClass(this);


        findViewById(R.id.btnSignIN).setOnClickListener(v->{
            userInfo = db.CheckUser(user.getText().toString(), pass.getText().toString());
            UserModel.userData=userInfo;
            if (userInfo!=null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("info",(Parcelable) userInfo);
                startActivity(intent);
                finishAffinity();
            }
        });


        findViewById(R.id.btnSignUp).setOnClickListener(v->{
            Intent intent = new Intent(this, RegisterAccount.class);
            startActivity(intent);
        });

    }
}