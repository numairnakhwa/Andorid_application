package com.example.abc.a32pearls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DisplayAppoint extends AppCompatActivity {
    DatabaseHelper_Appoint dhp;
    public EditText show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_appoint);
        dhp=new DatabaseHelper_Appoint(this);
        show=findViewById(R.id.show);
        String x=dhp.selectdata();
        show.setText(x);
        show.setKeyListener(null);
    }
}
