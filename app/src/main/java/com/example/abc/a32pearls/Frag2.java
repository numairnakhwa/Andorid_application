package com.example.abc.a32pearls;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ABC on 3/7/2018.
 */

public class Frag2 extends Fragment {

    public TextView dateview2;
    public Button selectdate2;
    public Calendar pickdate;
    public int day,month,year;
    public String updatedtable;

    DatabaseHelper_Appoint dhp;
    public EditText show;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.frag2,container,false);
        dhp=new DatabaseHelper_Appoint((TabFragments)getActivity());
        dateview2=v.findViewById(R.id.dateview2);
        choosedate();
        show=v.findViewById(R.id.show);
        String x=dhp.selectdata();
        show.setText(x);
        show.setKeyListener(null);
        return v;
    }

    public  void choosedate(){


        pickdate=Calendar.getInstance();

        day=pickdate.get(Calendar.DAY_OF_MONTH);
        month=pickdate.get(Calendar.MONTH);
        year=pickdate.get(Calendar.YEAR);
        month=month+1;
        dateview2.setText(day+"/"+month+"/"+year);



        selectdate2=v.findViewById(R.id.Date2);
        selectdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd=new DatePickerDialog((TabFragments)getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;

                        dateview2.setText(i2+"/"+i1+"/"+i);
                        updatedtable=dhp.updatedselectdata(dateview2.getText().toString());
                        show.setText(updatedtable);

                    }
                },year+0,month-1, day+0 );

                dpd.show();


            }
        });
    }
}
