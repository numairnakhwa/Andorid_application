package com.example.abc.a32pearls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mandibular extends AppCompatActivity implements View.OnClickListener{
    public Button maxillary ;
    Combine c;
    public void init() {
        maxillary =  findViewById(R.id.maxillary);
        maxillary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1 = new Intent(Mandibular.this,Maxillary2.class);
                startActivity(i1);
            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandibular);
        init();
        c=new Combine(Mandibular.this);
    }

    @Override
    public void onClick(View view) {
        Button b=findViewById(view.getId());
        String teeth_name2=b.getText().toString();
        c.setTooth(teeth_name2);
        Toast.makeText(this,teeth_name2,Toast.LENGTH_LONG).show();
        startActivity(new Intent(Mandibular.this,ChooseTreatment.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Mandibular.this,Activity1.class));
    }
}
