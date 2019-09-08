package com.example.shivam.favourite;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Shivam on 06-09-2019.
 */

public class CustomAdapter extends BaseAdapter{
    ArrayList<DataModel> data;
    Context c;
    LayoutInflater layoutInflater;
    DB db;
    CustomAdapter(Context context,ArrayList<DataModel> data){
        this.data=data;
        c=context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DataModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View v, ViewGroup viewGroup){
        v=layoutInflater.inflate(R.layout.list_layout,null);
        TextView textView=v.findViewById(R.id.item);
        final ToggleButton fav=v.findViewById(R.id.fav);
        textView.setText(data.get(pos).getItem());
        db=new DB(c);
        if(data.get(pos).getFav()==1) {
            fav.setBackgroundResource(R.drawable.ic_star_black_24dp);
        }
        else {
            fav.setBackgroundResource(R.drawable.un_fav);
        }
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    fav.setBackgroundResource(R.drawable.un_fav);
                    db.updateData(data.get(pos).getItem(),0);
                    if(c.getClass().getSimpleName().equals("ViewFavs")){
                        data.remove(pos);
                        notifyDataSetChanged();
                    }
                }
                else{
                        fav.setBackgroundResource(R.drawable.ic_star_black_24dp);
                        db.updateData(data.get(pos).getItem(),1);
                }
            }
        });
        return v;
    }

}
