package com.example.abc.a32pearls;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABC on 2/22/2018.
 */

public class DatabaseHelper_Appoint extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "patient2.db";                                                        //name of the database
    public static final String TABLE_NAME = "appoint_data";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Day";
    public static final String COL_3 = "From_Time";
    public static final String COL_4= "To_Time";
    public static final String COL_5= "whole";


    public DatabaseHelper_Appoint(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " Text," + COL_2 + " Date," + COL_3 + " Text," + COL_4 + " Text," + COL_5 + " Int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);

    }

    public void insertappoint(String p_name, String d_ay, String from_time,String to_time,int whole) {
        ContentValues cv = new ContentValues();
        cv.put(COL_1, p_name);
        cv.put(COL_2, d_ay);
        cv.put(COL_3, from_time);
        cv.put(COL_4,to_time);
        cv.put(COL_5,whole);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
        /*long result=

        if(result==1)
            return true;
        else
            return false;*/


    }

    public String selectdata(){
        SQLiteDatabase db1=this.getWritableDatabase();
        String[] cols={COL_1,COL_2,COL_3,COL_4};
        Cursor c=db1.query(TABLE_NAME,cols,null,null,null,null,COL_2);
        StringBuffer sb=new StringBuffer();
        while (c.moveToNext()){
            String a=c.getString(0);
            String b=c.getString(1);
            String x=c.getString(2);
            String y=c.getString(3);
            sb.append("Name:    "+a+"\nDate:      "+b+"\nFROM:    "+x+"\nTO:         "+y+"\n"+"\n---------------------------------\n");
        }
        return sb.toString();
    }

    public String updatedselectdata(String upday){
        SQLiteDatabase db1=this.getWritableDatabase();
        String[] cols={COL_1,COL_2,COL_3,COL_4};
        Cursor c=db1.rawQuery("select * from "+TABLE_NAME+" where "+COL_2+"='"+upday+"'",null);
        StringBuffer sb=new StringBuffer();
        while (c.moveToNext()){
            String a=c.getString(0);
            String b=c.getString(1);
            String x=c.getString(2);
            String y=c.getString(3);
            sb.append("Name:    "+a+"\nDate:      "+b+"\nFROM:    "+x+"\nTO:         "+y+"\n"+"\n---------------------------------\n");
        }
        return sb.toString();
    }

    public void deletedata(String n_ame){
        SQLiteDatabase db1=this.getReadableDatabase();
        db1.delete(TABLE_NAME, COL_1 + " = ?", new String[] { n_ame });

    }

    public void updatetable(String p_name, String d_ay, String from_time,String to_time){
        SQLiteDatabase dbup=this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, d_ay);
        cv.put(COL_3, from_time);
        cv.put(COL_4,to_time);
        dbup.update(TABLE_NAME,cv,COL_1 +" = ?",new String[] {p_name});
    }
    public void deleteappoint(String n_ame){
        SQLiteDatabase db1=this.getReadableDatabase();
        db1.delete(TABLE_NAME, COL_5 + " < ?", new String[] { n_ame });

    }



}
