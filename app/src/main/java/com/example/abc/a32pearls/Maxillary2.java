package com.example.abc.a32pearls;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Maxillary2 extends AppCompatActivity implements View.OnClickListener {
    public Button mandibular ;
    Combine c;
    public  String teeth_name;


    public void init() {
       mandibular =findViewById(R.id.mandibular);
       mandibular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(Maxillary2.this, Mandibular.class);
                startActivity(i2);
                i2.putExtra("tooth1",mandibular.getText().toString());
         }


        });
   }
    public Button t1;
    /*public Button t2=(Button) findViewById(R.id.t2);
    public Button t3;
    public Button t4;
    public Button t5;
    public Button t6;
    public Button t7;
    public Button t8;
    public Button t9;
    public Button t10;
    public Button t11;
    public Button t12;
    public Button t13;
    public Button t14;
    public Button t15;
    public Button t16;*/
   public void cTreat(){
        t1= findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Maxillary2.this,ChooseTreatment.class));
            }
        });


   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxillary2);
        c=new Combine(Maxillary2.this);
        init();


    }


    @Override
    public void onClick(View view) {
       Intent i=new Intent(Maxillary2.this,ChooseTreatment.class);
       startActivity(i);

        //startActivity(new Intent(Maxillary2.this,ChooseTreatment.class));
        Button b=findViewById(view.getId());
        String teeth_name2=b.getText().toString();
        c.setTooth(teeth_name2);
        Toast.makeText(this,teeth_name2,Toast.LENGTH_LONG).show();





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Maxillary2.this,Activity1.class));
    }


}
