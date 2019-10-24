package com.example.abc.a32pearls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExistingActivity extends AppCompatActivity {

    public Button search,next,nameview,deleteEx;
    public EditText patient_name,nameviewtext;
    DatabaseHelper_NewloginAc2 dbname;
    DatabaseHelper_Existing dbex;
    Combine c1;
    boolean b1=false;
    public EditText name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing);
        c1=new Combine(ExistingActivity.this);

        search=findViewById(R.id.search);
        next=findViewById(R.id.next);
        nameview=findViewById(R.id.nameview);
        patient_name=findViewById(R.id.patientname);
        nameviewtext=findViewById(R.id.nameviewtext);
        nameviewtext.setKeyListener(null);
        deleteEx=findViewById(R.id.delete);


        dbname=new DatabaseHelper_NewloginAc2(this);
        dbex=new DatabaseHelper_Existing(this);
        //String x=dbname.givenames("");
        //nameviewtext.setText(x);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x=dbname.selectdata(patient_name.getText().toString());
                nameviewtext.setText(x);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=dbname.selectdata2(patient_name.getText().toString());
                check();
                if (b1 && count>0) {
                    c1.setP_name(patient_name.getText().toString());
                    startActivity(new Intent(ExistingActivity.this, Maxillary2.class));
                }
                else Toast.makeText(ExistingActivity.this,"Wrong name \n enter complete valid name",Toast.LENGTH_LONG).show();
            }
        });

        nameview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String y=dbex.updatedselectdata(patient_name.getText().toString());
                nameviewtext.setText(y);
                if(y=="" && patient_name.getText().toString()!="")
                    Toast.makeText(ExistingActivity.this,"enter valid name",Toast.LENGTH_SHORT).show();
                else if(y=="")
                    Toast.makeText(ExistingActivity.this,"enter name",Toast.LENGTH_SHORT).show();

            }
        });

        deleteEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String y=patient_name.getText().toString();
                dbex.deletedata(y);
                dbname.deletedata(y);
                startActivity(new Intent(ExistingActivity.this,ExistingActivity.class));
            }
        });



    }
    private void check() {

        b1=true;
        name1= findViewById(R.id.patientname);
        String name_check= name1.getText().toString();
        if(name_check.isEmpty()){
            Toast.makeText(ExistingActivity.this, " Enter a name ", Toast.LENGTH_LONG).show();
            b1=false;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ExistingActivity.this,Activity1.class));
    }
}
