package com.example.abc.a32pearls;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class DisplayBillLayout extends AppCompatActivity {

    BackgroundTask backgroundTask=new BackgroundTask(this);
    DatabaseHelper_Treatment dbt;
    TextView t6,name,idtext,agetext,gendertext;
    DatabaseHelper_NewloginAc2 dbnl2;
    Combine c1;
    private ConstraintLayout rootContent;

    public void getBackgroundTask() {
        backgroundTask.execute("get_info");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_bill_layout);
        getBackgroundTask();
        dbt=new DatabaseHelper_Treatment(this);
        dbnl2=new DatabaseHelper_NewloginAc2(this);
        c1=new Combine(DisplayBillLayout.this);

        name=findViewById(R.id.nametext);
        idtext=findViewById(R.id.idtext);
        agetext=findViewById(R.id.agetext);
        gendertext=findViewById(R.id.sextext);

        String[] info=dbnl2.pinfo2(c1.getP_name());
        name.setText(String.format("%s", info[1]));
        idtext.setText(String.format("%s", info[0]));
        agetext.setText(String.format("%s", info[2]));
        gendertext.setText(String.format("%s", info[3]));

        rootContent = findViewById(R.id.cl1);


        t6=findViewById(R.id.totaltext);
        String total=dbt.total()+"";
        t6.setText("₹"+ total);
        Button done= findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(DisplayBillLayout.this,Activity1.class);
                startActivity(i4);
                takeScreenshot();
                dbt.deletetable();

            }
        });

        Button addt= findViewById(R.id.addt);
        addt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DisplayBillLayout.this,Maxillary2.class);
                startActivity(i);

            }
        });

    }
    private void takeScreenshot() {
        Bitmap b = null;


                //If Screenshot type is FULL take full page screenshot i.e our root content.
                b = ScreenshotUtils.getScreenShot(rootContent);


        //If bitmap is not null
        if (b != null) {

            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            File file = ScreenshotUtils.store(b, "screenshot" + ".jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);//finally share screenshot
        } else
            //If bitmap is null show toast message
            Toast.makeText(this,"failed", Toast.LENGTH_SHORT).show();

    }
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }

}
