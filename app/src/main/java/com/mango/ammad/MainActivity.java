package com.mango.ammad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter myadapter;
    RecyclerView RV;
    static UserData userInfo=null;
    List data = new ArrayList<DataTableModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo=getIntent().getParcelableExtra("info");

        Toast.makeText(this, ""+userInfo.getId(), Toast.LENGTH_SHORT).show();

        //
        dbHelperClass dbHelperClass = new dbHelperClass(this);
        data = dbHelperClass.getDataModels(userInfo.getId());
        dbHelperClass.close();
        RV = findViewById(R.id.recycler);
        myadapter = new MyAdapter(data, this);
        RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RV.setAdapter(myadapter);

        findViewById(R.id.btnNew).setOnClickListener(v->{
            Intent intent = new Intent(this,itemEntry.class);
            intent.putExtra("info",(Parcelable) userInfo);
            startActivity(intent);
        });


        findViewById(R.id.txtLogOut).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
            UserModel.userData=null;
            finishAffinity();
        });
    }
}