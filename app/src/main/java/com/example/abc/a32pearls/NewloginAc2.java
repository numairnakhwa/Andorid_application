
package com.example.abc.a32pearls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewloginAc2 extends AppCompatActivity {
    public Button add;
    boolean b1=false;
    boolean b2=false;
    boolean b3=false;
    public EditText name1;  //=findViewById(R.id.Name)
    public EditText age;  //=findViewById(R.id.Age)
    DatabaseHelper_NewloginAc2 myDB;

    public RadioGroup rg;
    public String sex;
    Combine c1;






    public void init(){

        add= findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                check();
                int count=myDB.selectdata2(name1.getText().toString());
                if(b1 && b2 && b3 && count==0){
                    //if(count==0) {

                        c1.setP_name(name1.getText().toString());
                        myDB.insertdata(name1.getText().toString().toLowerCase(), Integer.parseInt(age.getText().toString()), sex + "");


                        Intent i3 = new Intent(NewloginAc2.this, Maxillary2.class);
                        startActivity(i3);
                    //}
                   // else Toast.makeText(NewloginAc2.this,"Patient Already exists",Toast.LENGTH_LONG).show();
                }
                if(count!=0) Toast.makeText(NewloginAc2.this,"Already exists",Toast.LENGTH_LONG).show();


            }
        });
    }

    private void check() {

        b1=true;
        name1= findViewById(R.id.Name);
        String name_check= name1.getText().toString();
        if(name_check.isEmpty()){
            Toast.makeText(NewloginAc2.this, " Enter a name ", Toast.LENGTH_LONG).show();
            b1=false;
        }


        age=findViewById(R.id.Age);
        try {
            int age_check = Integer.parseInt(age.getText().toString());
            if(age_check>0) {
                b2 = true;
            }
        }catch (Exception e){
            Toast.makeText(NewloginAc2.this," Enter proper age in numbers ",Toast.LENGTH_LONG).show();
            b2=false;
        }




        try {
            rg=findViewById(R.id.radioGroup);
            RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
            sex = rb.getText().toString();
            if (sex.equals("Male") || sex.equals("Female")) {
                b3 = true;

            }
        }
        catch (Exception e){
            Toast.makeText(NewloginAc2.this,"select gender:",Toast.LENGTH_LONG).show();
        }


    } //form validation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlogin_ac2);
        init();
        myDB=new DatabaseHelper_NewloginAc2( this);
        c1=new Combine(NewloginAc2.this);


    }
}

