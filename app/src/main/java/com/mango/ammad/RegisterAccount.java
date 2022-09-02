package com.mango.ammad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAccount extends AppCompatActivity {

    UserData userData=null;
    EditText []editTexts=new EditText[5];
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        btn=findViewById(R.id.btnRegSignUp);

        editTexts[0]=findViewById(R.id.editSignEmail);
        editTexts[1]=findViewById(R.id.editRollNumber);
        editTexts[2]=findViewById(R.id.editEmail);
        editTexts[3]=findViewById(R.id.editPassword);
        editTexts[4]=findViewById(R.id.editSignPassword);


        dbHelperClass dbHelperClass = new dbHelperClass(this);

        btn.setOnClickListener(v->{
                    String p1=editTexts[3].getText().toString();
                    String p2=editTexts[4].getText().toString();

                    if(checkIfViewEmpty(editTexts[0],editTexts[1],editTexts[2],editTexts[3],editTexts[4]))
                        Toast.makeText(this, "Some Fields are Empty ", Toast.LENGTH_SHORT).show();
                    else if(!(p1.equals(p2)))
                        Toast.makeText(this, "Make Sure Password = ConfirmPassword", Toast.LENGTH_LONG).show();
                    else{
                        dbHelperClass.RegisterUser(new UserData(
                                editTexts[0].getText().toString(),
                                editTexts[1].getText().toString(),
                                editTexts[2].getText().toString(),
                                editTexts[3].getText().toString()
                        ));

                        Intent intent= new Intent(getApplicationContext(),LoginPage.class);
                        startActivity(intent);
                        finishAffinity();
                    }

                }


        );
    }



    boolean checkIfViewEmpty(EditText ed1,EditText ed2,EditText ed3,EditText ed4,EditText ed5){

        return
                ed1.getText().toString().isEmpty() &&
                        ed2.getText().toString().isEmpty() &&
                        ed3.getText().toString().isEmpty() &&
                        ed4.getText().toString().isEmpty() &&
                        ed5.getText().toString().isEmpty();
    }
}