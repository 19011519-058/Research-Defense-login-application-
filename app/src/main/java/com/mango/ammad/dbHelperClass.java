package com.mango.ammad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class dbHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RegistartionDB";
    public static final int DATABASE_VERSION = 1;

    Context context;

    public dbHelperClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(" CREATE TABLE " + UserModel.USER_TABLE
                + "("
                + UserModel.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserModel.KEY_NAME + " TEXT, "
                + UserModel.KEY_ROLL_NUMBER + " TEXT,"
                + UserModel.KEY_EMAIL + " TEXT, "
                + UserModel.KEY_PASSWORD + " TEXT "
                + ")");

        db.execSQL(" CREATE TABLE "+ DataTableModel.DATA_MODEL_TABLE
                +"("
                +DataTableModel.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +DataTableModel.KEY_FOREIGN_KEY+" INTEGER ,"
                +DataTableModel.KEY_Student+" TEXT ,"
                +DataTableModel.KEY_Course+" TEXT ,"
                +" FOREIGN KEY ("
                +DataTableModel.KEY_FOREIGN_KEY
                +") REFERENCES "
                + UserModel.USER_TABLE
                + "("
                + UserModel.KEY_ID
                + ") "
                +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserModel.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DataTableModel.DATA_MODEL_TABLE);
        onCreate(db);
    }


    public void RegisterUser(UserData user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserModel.KEY_NAME, user.getName());
        contentValues.put(UserModel.KEY_EMAIL, user.getEmail());
        contentValues.put(UserModel.KEY_PASSWORD, user.getPassword());
        contentValues.put(UserModel.KEY_ROLL_NUMBER, user.getRollNumber());
        sqLiteDatabase.insert(UserModel.USER_TABLE, null, contentValues);
    }


    public UserData CheckUser(String user, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + UserModel.USER_TABLE
                + " where " + UserModel.KEY_EMAIL + " =? " + " and " + UserModel.KEY_PASSWORD + " =?", new String[]{user, password});

        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            return new UserData(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }
        return null;
    }


    public void insertCourse(DataModel DM,int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataTableModel.KEY_FOREIGN_KEY,id);
        contentValues.put(DataTableModel.KEY_Student,DM.getName());
        contentValues.put(DataTableModel.KEY_Course,DM.getCourse());
        sqLiteDatabase.insert(DataTableModel.DATA_MODEL_TABLE,null,contentValues);
    }


    public ArrayList<DataModel> getDataModels(int id){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor= db.rawQuery(" select * from "+DataTableModel.DATA_MODEL_TABLE +" where " +
                DataTableModel.KEY_FOREIGN_KEY +" =? ",new String[]{String.valueOf(id)});

        ArrayList<DataModel> data = new ArrayList<>();

        while (cursor.moveToNext()){
            data.add(new DataModel(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }

        return data;
    }

    void removeDataModel(int id){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(DataTableModel.DATA_MODEL_TABLE,DataTableModel.KEY_ID+" =? ",new String[]{String.valueOf(id)});
    }

    void updateCourse(DataModel DM){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataTableModel.KEY_Student, DM.getName());
        cv.put(DataTableModel.KEY_Course, DM.getCourse());

        db.update(DataTableModel.DATA_MODEL_TABLE,cv,DataTableModel.KEY_ID+" =? "
                ,new String[]{String.valueOf(DM.getId())});
    }

}

