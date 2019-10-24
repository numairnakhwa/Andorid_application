package com.example.abc.a32pearls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 1/29/2018.
 */

public class DatabaseHelper_NewloginAc2 extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="patient.db";                                                        //name of the database
    public static final String TABLE_NAME="patient_data";
    public static final String COL_1="id";
    public static final String COL_2="p_name";
    public static final String COL_3="age";
    public static final String COl_4="gender";



    public DatabaseHelper_NewloginAc2(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"("+COL_1+" Integer primary key autoincrement,"+COL_2+" Text,"+COL_3+" Integer,"+COl_4+" Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+TABLE_NAME);

    }

    public void insertdata(String name,Integer age,String sex){
        ContentValues cv= new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,age);
        cv.put(COl_4,sex);
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
        /*long result=

        if(result==1)
            return true;
        else
            return false;*/


    }
    public String[] pinfo(){
        SQLiteDatabase dbinfo=this.getReadableDatabase();
        String[] allinfo = new String[4];
        Cursor pinfo=dbinfo.rawQuery("select * from "+TABLE_NAME+"",null);
        while (pinfo.moveToNext()){
            String a=pinfo.getString(0);
            String b=pinfo.getString(1);
            String x=pinfo.getString(2);
            String y=pinfo.getString(3);
            allinfo = new String[]{a, b, x, y};

        }
        return allinfo;
    }

    public String[] pinfo2(String patientname){
        SQLiteDatabase dbinfo=this.getReadableDatabase();
        String[] allinfo = new String[4];
        Cursor pinfo=dbinfo.rawQuery("select * from "+TABLE_NAME+" where p_name = '"+patientname+"'",null);
        while (pinfo.moveToNext()){
            String a=pinfo.getString(0);
            String b=pinfo.getString(1);
            String x=pinfo.getString(2);
            String y=pinfo.getString(3);
            allinfo = new String[]{a, b, x, y};

        }
        return allinfo;
    }

    public String selectdata(String substring){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor c=db1.rawQuery("select p_name from "+TABLE_NAME+" where p_name like '%"+substring+"%'",null);
        StringBuffer sb=new StringBuffer();
        while (c.moveToNext()){
            String a=c.getString(0);
            sb.append("Name:    "+a+"\n"+"\n---------------------------------\n");
        }
        return sb.toString();
    }
        //to check the existence of the typed name
    public int selectdata2(String existPname){
        int count=0;
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor c=db1.rawQuery("select p_name from "+TABLE_NAME,null);
        StringBuffer sb=new StringBuffer();
        while (c.moveToNext()){
            String a=c.getString(0);
            if(existPname.toLowerCase().equals(a.toLowerCase())){
            count+=1;
            }
        }

        return count;
    }
    public void deletedata(String n_ame){
        SQLiteDatabase db1=this.getReadableDatabase();
        db1.delete(TABLE_NAME, COL_2 + " = ?", new String[] { n_ame });

    }




}
