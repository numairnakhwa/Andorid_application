package com.example.abc.a32pearls;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Activity1 extends Activity {
    public Button b2;
    public Button b3;
    public Button b4;
    public void init(){

        b4=findViewById(R.id.btnexisting);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity1.this,ExistingActivity.class));
            }
        });

        b2=  findViewById(R.id.btnnew);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Activity1.this,NewloginAc2.class);
                startActivity(i1);
            }


        });

        b3=findViewById(R.id.btnnew2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Activity1.this,TabFragments.class);
                startActivity(i2);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_1);
        init();

    }
}
