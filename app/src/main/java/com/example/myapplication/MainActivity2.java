package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    List<Place> list;
    AdapterPalce adapter;
    TextView txt;
    Button btnSave,btnCan;

    int index = -1;

    private static MainActivity2 instance;
    private static DatabasePlace db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        instance = this;

        db  = new DatabasePlace(this);

        db.deletePlace(2);
        db.deletePlace(3);
        db.deletePlace(4);


        listView = findViewById(R.id.listViewPlace);
        txt = findViewById(R.id.edtInputPlace);
        btnSave = findViewById(R.id.btnSave);

        list = new ArrayList<>();
        list = db.getAllPlace();
        adapter = new AdapterPalce(this,R.layout.item_place,list);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = new Place(txt.getText().toString());
                db.addPlace(place);
                txt.setText("");
                update();
            }
        });
    }

    public static MainActivity2 getInstance(){
        return instance;
    }

    public static DatabasePlace getDb() {
        return db;
    }

    public void update() {
        list.clear();
        List<Place> temp = db.getAllPlace();
        for (Place n : temp){
            list.add(n);
        }
        adapter.notifyDataSetChanged();
    }

}