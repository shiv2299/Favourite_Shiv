package com.example.shivam.favourite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFavs extends AppCompatActivity {

    ListView FavlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favs);
        FavlistView=findViewById(R.id.Favlistview);
        ArrayList<DataModel> arrayList=new ArrayList<>();
        String item;
        int fav;
        DB db=new DB(this);
        Cursor cursor = db.getFav();
        while (cursor.moveToNext()) {
            item = cursor.getString(0);
            fav = cursor.getInt(1);
            arrayList.add(new DataModel(item, fav));
        }
        CustomAdapter customAdapter =new CustomAdapter(this,arrayList);
        FavlistView.setAdapter(customAdapter);
    }
}
