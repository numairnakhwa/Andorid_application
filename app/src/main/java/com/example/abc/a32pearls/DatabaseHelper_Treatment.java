package com.example.abc.a32pearls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABC on 2/25/2018.
 */

public class DatabaseHelper_Treatment extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="patient1.db";                                                        //name of the database
    public static final String TABLE_NAME="Treatment_data";
    public static final String COL_1="Tooth";
    public static final String COL_2="Treatment_name";
    public static final String COL_3="Price";







    public DatabaseHelper_Treatment(Context context) {
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"("+COL_1+" Text,"+COL_2+" Text,"+COL_3+" Integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+TABLE_NAME);

    }

    public void insertdata(String tooth,String name,Integer price){
        ContentValues cv= new ContentValues();
        cv.put(COL_1,tooth);
        cv.put(COL_2,name);
        cv.put(COL_3,price);
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
        /*long result=

        if(result==1)
            return true;
        else
            return false;*/


    }
    public int total(){
        SQLiteDatabase db3=this.getReadableDatabase();
        Cursor price=db3.rawQuery("select sum("+COL_3+") from "+TABLE_NAME+"",null);
        if (price.moveToFirst()){
            Integer total=price.getInt(0);
            return total;
        }
        else return 0;



    }
    public void deletetable(){
        SQLiteDatabase db3=this.getReadableDatabase();
        db3.delete(TABLE_NAME,null,null);
    }

    public Cursor getbill(SQLiteDatabase db2){
        String[] projections={COL_1,COL_2,COL_3};
        Cursor res=db2.query(TABLE_NAME,projections,null,null,null,null,null);

        return res;
    }
}
