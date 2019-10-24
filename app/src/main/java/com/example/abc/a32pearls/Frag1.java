package com.example.abc.a32pearls;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ABC on 3/7/2018.
 */

public class Frag1 extends Fragment {

    public TextView dateview;
    public Button selectdate;
    public Calendar pickdate;
    public int day,month,year,whole;

    public static String y="";

    public TextView from_timeview;
    public TextView to_timeview;

    public Calendar picktime;
    public int hour,minute;
    public String format;
    DatabaseHelper_Appoint db3;

    public Button fix,deleteb,updateb;
    public EditText p_name;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.frag1,container,false);
        dateview=v.findViewById(R.id.dateview1);
        from_timeview=v.findViewById(R.id.fromtimeview1);
        to_timeview=v.findViewById(R.id.totimeview1);
        choosedate();
        choosetime();
        db3=new DatabaseHelper_Appoint((TabFragments)getActivity());
        fix=v.findViewById(R.id.Fix);
        p_name=v.findViewById(R.id.editText);
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db3.insertappoint(p_name.getText().toString().toLowerCase(),dateview.getText().toString(),from_timeview.getText().toString(),to_timeview.getText().toString(),whole);
                Toast.makeText((TabFragments)getActivity(),"successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent((TabFragments)getActivity(),TabFragments.class));

            }
        });
        deletedata();

        updateb=v.findViewById(R.id.updateb);
        updateb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db3.updatetable(p_name.getText().toString(),dateview.getText().toString(),from_timeview.getText().toString(),to_timeview.getText().toString());
                Toast.makeText((TabFragments)getActivity(),"successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent((TabFragments)getActivity(),TabFragments.class));

            }
        });


        return v;


    }

    public  void choosedate(){


        pickdate=Calendar.getInstance();

        day=pickdate.get(Calendar.DAY_OF_MONTH);
        month=pickdate.get(Calendar.MONTH);
        year=pickdate.get(Calendar.YEAR);
        month=month+1;
        whole=Integer.parseInt(year+""+month+""+day);
        dateview.setText(day+"/"+month+"/"+year);



        selectdate=v.findViewById(R.id.Date1);
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd=new DatePickerDialog((TabFragments)getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;

                        dateview.setText(i2+"/"+i1+"/"+i);
                        whole=Integer.parseInt(i+""+i1+""+i2);

                    }
                },year+0,month-1, day+0 );

                dpd.show();
            }
        });
    }

    public  void choosetime(){


        picktime=Calendar.getInstance();

        hour=picktime.get(Calendar.HOUR_OF_DAY);
        minute=picktime.get(Calendar.MINUTE);


        hour=selectedtimeformat(hour);

        from_timeview.setText(hour+":"+minute+" "+format);
        thirtymin();


        from_timeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog tpd=new TimePickerDialog((TabFragments)getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        i=selectedtimeformat(i);
                        from_timeview.setText(i+":"+i1+" "+format);



                    }
                },pickdate.get(Calendar.HOUR_OF_DAY),picktime.get(Calendar.MINUTE),false);
                tpd.show();

            }
        });


        to_timeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog tpd=new TimePickerDialog((TabFragments)getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        i=selectedtimeformat(i);
                        to_timeview.setText(i+":"+i1+" "+format);


                    }
                },pickdate.get(Calendar.HOUR_OF_DAY),picktime.get(Calendar.MINUTE),false);
                tpd.show();
            }
        });




    }

    public int selectedtimeformat(int hours){
        if(hours>12){
            hours-=12;
            format="PM";
            return hours;
        }

        else {
            format="AM";
            return hours;
        }

    }

    public void thirtymin(){
        if (minute+30>60){
            minute=(minute+30)-60;
            hour=hour+1;
            if (format.equals("AM") && hour==12)
                format="PM";
            if (format.equals("PM") && hour==12)
                format="AM";
            to_timeview.setText(hour+":"+minute+" "+format);
        }
        else
            to_timeview.setText(hour+":"+(minute+30)+" "+format);


    }

    public void deletedata(){
        deleteb=v.findViewById(R.id.deleteb);
        deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y=p_name.getText().toString();
                db3.deletedata(y);
                startActivity(new Intent((TabFragments)getActivity(),TabFragments.class));

            }
        });

    }
}
