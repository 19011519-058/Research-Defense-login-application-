package com.mango.ammad;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VHolder> {

    List <DataModel> Data;
    private Activity activity;

    public MyAdapter(List<DataModel> data, Activity activity) {
        Data = data;
        this.activity = activity;
    }

    public static class VHolder extends RecyclerView.ViewHolder {
        TextView Student;
        TextView Course;
        Button Delete;
        CardView Edit;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            Student = itemView.findViewById(R.id.Student);
            Course = itemView.findViewById(R.id.Course);
            Delete = itemView.findViewById(R.id.btnDelete);
            Edit = itemView.findViewById(R.id.card);

        }
    }
    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.courseitem, parent, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        DataModel mydata = Data.get(position);
        holder.Student.setText(mydata.getName());
        holder.Course.setText(mydata.getCourse());
        holder.Delete.setOnClickListener(v -> {
            dbHelperClass dbHelperClass =new dbHelperClass(activity);
            dbHelperClass.removeDataModel(mydata.getId());
            dbHelperClass.close();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("info",(Parcelable) UserModel.userData);
            activity.startActivity(intent);
            activity.finishAffinity();
        });
        holder.Edit.setOnClickListener(v -> {

            Intent intent = new Intent(activity,itemEntry.class);
            intent.putExtra("Edit", mydata);
            intent.putExtra("pos",position);
            intent.putExtra("info",(Parcelable) UserModel.userData);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

}