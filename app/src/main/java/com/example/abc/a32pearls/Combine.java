package com.example.abc.a32pearls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ABC on 2/27/2018.
 */

public class Combine {
    Context c;
    SharedPreferences pref;

    public String getTooth() {
        tooth=pref.getString("teethdata","");
        return tooth;
    }

    public void setTooth(String tooth) {
        this.tooth = tooth;
        pref.edit().putString("teethdata",tooth).commit();
    }

    private String tooth;

    public String getP_name() {
        p_name=pref.getString("patient_name","");
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
        pref.edit().putString("patient_name",p_name).commit();
    }

    private String p_name;

    public Combine(Context con){
        this.c=con;
        pref=con.getSharedPreferences("userinfo",Context.MODE_PRIVATE);


    }
}
