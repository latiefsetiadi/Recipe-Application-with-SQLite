package com.example.myrecipe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class databaseHelper extends SQLiteOpenHelper {
    public databaseHelper(@Nullable Context context) {
        super(context, Constans.DB_NAME, null, Constans.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constans.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constans.TABLE_NAME);
        onCreate(db);
    }

    public long insertInfo(String namefood,String namechef, String bahan, String step,
                           String image, String addTimeStamp,
                           String updateTimeStamp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constans.C_NAMEFOOD, namefood);
        values.put(Constans.C_NAMECHEF, namechef);
        values.put(Constans.C_BAHAN, bahan);
        values.put(Constans.C_STEP, step);
        values.put(Constans.C_IMAGE, image);
        values.put(Constans.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constans.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id = db.insert(Constans.TABLE_NAME, null, values);
        db.close();
        return id;

    }
    public void updateInfo(String id,String namefood,String namechef, String bahan, String step,
                           String image, String addTimeStamp,
                           String updateTimeStamp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constans.C_NAMEFOOD, namefood);
        values.put(Constans.C_NAMECHEF, namechef);
        values.put(Constans.C_BAHAN, bahan);
        values.put(Constans.C_STEP, step);
        values.put(Constans.C_IMAGE, image);
        values.put(Constans.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constans.C_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(Constans.TABLE_NAME, values, Constans.C_ID + " = ?", new String[]{id});
        db.close();
    }
    public void delteInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constans.TABLE_NAME, Constans.C_ID + " = ? ", new String[]{id});
        db.close();
    }
    public ArrayList<Model> getAllData(String orderBy){
        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constans.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){

            do {

                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constans.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_NAMEFOOD)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_NAMECHEF)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_BAHAN)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_STEP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constans.C_UPDATE_TIMESTAMP))
                );
                arrayList.add(model);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}
