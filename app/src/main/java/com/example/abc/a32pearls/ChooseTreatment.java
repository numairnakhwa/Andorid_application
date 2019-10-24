package com.example.abc.a32pearls;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class ChooseTreatment extends AppCompatActivity {
    ListView lst;
    TextView tv;
    Integer t_price;
    DatabaseHelper_Treatment db3;
    DatabaseHelper_Existing db4;
    Maxillary2 max2;
    String Tooth_name;
    Combine c2;
    public Calendar pickdate;
    public int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_treatment);
        c2=new Combine(ChooseTreatment.this);
        lst=  findViewById(R.id.lst);
        db3=new DatabaseHelper_Treatment( this);
        db4=new DatabaseHelper_Existing(this);
        ArrayAdapter<String> arrayadapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Treatment));
        lst.setAdapter(arrayadapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv= (TextView) view;
                Integer pos=position;

                Integer price;

                switch(pos){
                    case 0:
                       price=2000;
                       break;
                    case 1:
                        price=2500;
                        break;
                    case 2:
                        price=50;
                        break;
                    case 3:
                        price=50;
                        break;
                    case 4:
                        price=50;
                        break;
                    case 5:
                        price=400;
                        break;
                    case 6:
                        price=450;
                        break;
                    case 7:
                        price=500;
                        break;
                    case 8:
                        price=600;
                        break;
                    case 9:
                        price=650;
                        break;
                    case 10:
                        price=700;
                        break;
                    case 11:
                        price=250;
                        break;
                    case 12:
                        price=500;
                        break;
                    case 13:
                        price=1500;
                        break;
                    case 14:
                        price=1500;
                        break;
                    case 15:
                        price=2500;
                        break;
                    case 16:
                        price=100;
                        break;
                    case 17:
                        price=3500;
                        break;
                    case 18:
                        price=400;
                        break;
                    case 19:
                        price=450;
                        break;
                    case 20:
                        price=500;
                        break;
                    default:
                        price=0;
                }
                t_price=price;
                //Tooth_name=max2.teeth_name;
                Toast.makeText(ChooseTreatment.this,tv.getText()+"  price="+price+" on "+c2.getTooth(),Toast.LENGTH_LONG).show();

                db3.insertdata(c2.getTooth(),tv.getText().toString(),price);
                //db3.deletetable();

                pickdate= Calendar.getInstance();

                day=pickdate.get(Calendar.DAY_OF_MONTH);
                month=pickdate.get(Calendar.MONTH);
                year=pickdate.get(Calendar.YEAR);
                month=month+1;
                String ondate=(day+"/"+month+"/"+year);

                db4.insertdata(c2.getP_name(),ondate,c2.getTooth(),tv.getText().toString(),price);
                Intent i=new Intent(ChooseTreatment.this,DisplayBillLayout.class);
                startActivity(i);


            }
        });
    }


}