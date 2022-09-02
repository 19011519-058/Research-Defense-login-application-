package com.mango.ammad;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {
    private int id;
    private int userId;
    private String Name;
    private String Course;

    protected DataModel(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        Name = in.readString();
        Course = in.readString();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public DataModel(int id, int userId, String name, String course) {
        this.id = id;
        this.userId = userId;
        Name = name;
        Course = course;
    }

    public DataModel(String name, String course) {
        Name = name;
        Course = course;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(Name);
        dest.writeString(Course);
    }
}
