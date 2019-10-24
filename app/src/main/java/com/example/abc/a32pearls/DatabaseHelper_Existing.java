package com.example.abc.a32pearls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABC on 3/13/2018.
 */

public class DatabaseHelper_Existing extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "patient4.db";                                                        //name of the database
    public static final String TABLE_NAME = "patient_data";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Day";
    public static final String COL_3 = "Tooth";
    public static final String COL_4= "Treatment";
    public static final String COL_5= "price";


    public DatabaseHelper_Existing(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " Text," + COL_2 + " Date," + COL_3 + " Text,"+ COL_4 + " Text," + COL_5 + " Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);

    }

    public void insertdata(String p_name,String ondate,String tooth,String tname,Integer price){
        ContentValues cv= new ContentValues();
        cv.put(COL_1,p_name);
        cv.put(COL_2,ondate);
        cv.put(COL_3,tooth);
        cv.put(COL_4,tname);
        cv.put(COL_5,price);
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
        /*long result=

        if(result==1)
            return true;
        else
            return false;*/


    }

    public String updatedselectdata(String pn_ame){
        SQLiteDatabase db1=this.getWritableDatabase();
        String[] cols={COL_1,COL_2,COL_3,COL_4};
        Cursor c=db1.rawQuery("select * from "+TABLE_NAME+" where "+COL_1+"='"+pn_ame+"'",null);
        StringBuffer sb=new StringBuffer();
        while (c.moveToNext()){
            String a=c.getString(4);
            String b=c.getString(1);
            String x=c.getString(2);
            String y=c.getString(3);
            sb.append("\nDate:      "+b+"\nTooth:    "+x+"\nTreatment:         "+y+"\nprice:         "+a+"\n"+"\n---------------------------------\n");
        }
        return sb.toString();
    }
    public void deletedata(String n_ame){
        SQLiteDatabase db1=this.getReadableDatabase();
        db1.delete(TABLE_NAME, COL_1 + " = ?", new String[] { n_ame });

    }

}
