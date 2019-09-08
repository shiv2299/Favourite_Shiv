package com.example.shivam.favourite;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DB db;
    CustomAdapter customAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.listview);
        ArrayList<DataModel> arrayList=new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.add_item);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent = new Intent(MainActivity.this, AddItem.class);
                                       startActivity(intent);
                                   }
                            });

        String item;
        int fav;
        db=new DB(this);
        Cursor cursor = db.getAllData();
        while (cursor.moveToNext()) {
            item = cursor.getString(0);
            fav = cursor.getInt(1);
            arrayList.add(new DataModel(item, fav));
        }
        customAdapter =new CustomAdapter(this,arrayList);
        listView.setAdapter(customAdapter);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.btn,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_custom_button) {
            startActivity(new Intent(MainActivity.this,ViewFavs.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
