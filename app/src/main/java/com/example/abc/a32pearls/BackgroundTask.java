package com.example.abc.a32pearls;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ABC on 2/21/2018.
 */

public class BackgroundTask  extends AsyncTask<String,Product,String>{
    Context ctx;
    DatabaseHelper_Treatment myDB;
    ProductAdapter pa;
    Activity activity;
    ListView listView;
    BackgroundTask(Context ctx){
        this.ctx=ctx;
        activity=(Activity) ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method=params[0];
        myDB=new DatabaseHelper_Treatment(ctx);
        if(method.equals("get_info")) {
            listView=activity.findViewById(R.id.listview1);
            SQLiteDatabase db3 = myDB.getReadableDatabase();
            Cursor cursor = myDB.getbill(db3);
            pa =new ProductAdapter(ctx,R.layout.display_bill);
            String id_from_db, name_from_db;
            int age_from_db;
            while (cursor.moveToNext()) {
                id_from_db = cursor.getString(cursor.getColumnIndex(myDB.COL_1));
                name_from_db = cursor.getString(cursor.getColumnIndex(myDB.COL_2));
                age_from_db = cursor.getInt(cursor.getColumnIndex(myDB.COL_3));
                Product p = new Product(id_from_db, name_from_db, age_from_db);
                publishProgress(p);

            }
            return "get_info";
        }



        return null;
    }

    @Override
    protected void onProgressUpdate(Product... values) {
        pa.add(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("get_info")){
            listView.setAdapter(pa);
        }
        else
            Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();;
    }
}
