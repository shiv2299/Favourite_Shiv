package com.example.shivam.favourite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    EditText item_name;
    Button add;
    DB db;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item_name=findViewById(R.id.item_name);
        add=findViewById(R.id.add);

        db=new DB(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=true;
                String item=item_name.getText().toString();
                Cursor cursor=db.getAllData();
                while(cursor.moveToNext()){
                    if(item.equals(cursor.getString(0))) {
                        flag = false;
                        item_name.setError(item+" already in use ");
                    }
                }
                if(flag){
                    boolean f=db.insertData(item,0);
                    if(f) {
                        Toast.makeText(AddItem.this, item + " Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddItem.this,MainActivity.class));
                    }

                }
            }
        });
    }
}
