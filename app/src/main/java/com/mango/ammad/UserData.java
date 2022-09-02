package com.mango.ammad;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {
    private int id;
    private String name;
    private String rollNumber;
    private String email;
    private String password;

    public UserData() {
    }

    protected UserData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rollNumber = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserData(String name, String rollNumber, String email, String password) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.email = email;
        this.password = password;
    }

    public UserData(int id, String name, String rollNumber, String email, String password) {
        this.id=id;
        this.name = name;
        this.rollNumber = rollNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(rollNumber);
        dest.writeString(email);
        dest.writeString(password);
    }
}
