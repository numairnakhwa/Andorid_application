package com.example.abc.a32pearls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC on 2/21/2018.
 */

public class ProductAdapter extends ArrayAdapter {

    List l=new ArrayList();

    public ProductAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Product object) {
        l.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return l.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        holder h;
        if(row==null){
            LayoutInflater lf=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.display_bill,parent,false);
            h=new holder();
            h.id=row.findViewById(R.id.textView5);
            h.name=row.findViewById(R.id.textView6);
            h.age=row.findViewById(R.id.textView7);
            row.setTag(h);

        }
        else
            h=(holder) row.getTag();

        Product p = (Product) getItem(position);
        h.id.setText(p.getId().toString());
        h.name.setText(p.getName().toString());
        h.age.setText(Integer.toString(p.getAge()));

        return row;
    }

    static class holder{
        TextView id,name,age;
    }
}
