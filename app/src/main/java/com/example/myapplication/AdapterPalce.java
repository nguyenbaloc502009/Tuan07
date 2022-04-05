package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterPalce extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Place> list;
    private DatabasePlace db;

    public AdapterPalce(Context context, int idLayout, List<Place> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout,parent,false);

        }
        Place place = list.get(position);

        TextView txtId = convertView.findViewById(R.id.textViewPlaceId);
        EditText txtName = convertView.findViewById(R.id.textViewPlaceName);
        ImageButton btnU = convertView.findViewById(R.id.btnItemUpdate);
        ImageButton btnD = convertView.findViewById(R.id.btnItemDelete);

        txtId.setText(place.getId()+".");
        txtName.setText(place.getName());


        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place.setName(txtName.getText().toString());
                MainActivity2 instance = MainActivity2.getInstance();
                instance.getDb().updatePlace(place);
                Toast.makeText(instance, "Update place successfull", Toast.LENGTH_SHORT).show();
                instance.update();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity2 instance = MainActivity2.getInstance();
                instance.getDb().deletePlace(place.getId());
                Toast.makeText(instance, "Delete place successfull", Toast.LENGTH_SHORT).show();
                instance.update();
            }
        });

        return convertView;
    }
}