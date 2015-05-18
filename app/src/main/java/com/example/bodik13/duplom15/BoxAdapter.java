package com.example.bodik13.duplom15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.ArrayList;

/**
 * Created by Bodik13 on 31.03.2015.
 */
public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Travel> objects;

    public BoxAdapter(Context context, ArrayList<Travel> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }


    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Travel travel = getProduct(position);

        ((TextView) view.findViewById(R.id.firstName)).setText(travel.TAG_firstName + " " + travel.TAG_lastName);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(travel.TAG_price + " грн");
        ((TextView) view.findViewById(R.id.date_start)).setText(travel.TAG_dataFirst);

        ((TextView) view.findViewById(R.id.id_travel)).setText(travel.TAG_ID);

       // ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);


        return view;
    }

    // товар по позиции
    Travel getProduct(int position) {
        return ((Travel) getItem(position));
    }



}