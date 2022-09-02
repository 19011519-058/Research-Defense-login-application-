package com.mango.ammad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class itemEntry extends AppCompatActivity {

    EditText student,course;
    Button btn;
    static UserData userInfo;
    int pos=-1;
    DataModel data=null;
    DataModel dataEdit=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_entry);

        btn=findViewById(R.id.btnEntrySave);
        student=findViewById(R.id.editEntryName);
        course=findViewById(R.id.editEntryCourse);
        userInfo=getIntent().getParcelableExtra("info");
        pos= getIntent().getIntExtra("pos",-1);
        dataEdit=getIntent().getParcelableExtra("Edit");

        if (pos!=-1){
            student.setText(dataEdit.getName());
            course.setText(dataEdit.getCourse());
        }


        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("info",(Parcelable) userInfo);
            if (checkFilled(student,course)){
                Toast.makeText(this, "Some Fields Are Missing Please fill Those", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = student.getText().toString();
            String cs = course.getText().toString();

            if (pos!=-1)
                data= new DataModel(dataEdit.getId(),dataEdit.getUserId(),name,cs);
            else
                data= new DataModel(name,cs);

            dbHelperClass dbHelperClass = new dbHelperClass(this);
            if (pos==-1)
                dbHelperClass.insertCourse(data,userInfo.getId());
            else
                dbHelperClass.updateCourse(data);

            startActivity(intent);
            finishAffinity();
        });

    }

    boolean checkFilled(EditText a1, EditText a2){
        if (a1.getText().length()==0)return true;
        if (a2.getText().length()==0)return true;
        return false;
    }
}