
package com.example.cwitteacher.mymap;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MapStorageActivity extends AppCompatActivity {


    EditText etText;
    Button btAdd;
    ListView listView;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_storage);

        etText = findViewById(R.id.et_text);
        btAdd = findViewById(R.id.bt_add);
        listView = findViewById(R.id.list_view);

        databaseHelper = new DatabaseHelper(MapStorageActivity.this);

        arrayList = databaseHelper.getAllText();

        arrayAdapter = new ArrayAdapter(MapStorageActivity.this,
                android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etText.getText().toString();
                if(!text.isEmpty()){
                    if(databaseHelper.addText(text)){
                        etText.setText("");
                        Toast.makeText(getApplicationContext(),"Data Inserted....",Toast.LENGTH_SHORT).show();

                        arrayList.clear();
                        arrayList.addAll(databaseHelper.getAllText());

                        arrayAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                    }
                }
            }
        });
    }

}
