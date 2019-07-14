package com.example.myfirstsqlproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    Map<String, Double> map;
    List<String> names;
    List<Double> prices;

    public ItemAdapter (Context c, Map m){
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map = m;
        prices = new ArrayList<Double>(map.values());
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflator.inflate(R.layout.item_layout, null);
        TextView textViewName = v.findViewById(R.id.textViewName);
        TextView textViewPrice = v.findViewById(R.id.textViewPrice);

        textViewName.setText(names.get(i));
        textViewPrice.setText("$"+ prices.get(i).toString());

        return null;
    }
}
