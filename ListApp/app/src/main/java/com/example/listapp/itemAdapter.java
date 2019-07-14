package com.example.listapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class itemAdapter extends BaseAdapter {


    LayoutInflater mInflater;
    String [] items;
    String[] prices;
    String[] descriptions;

    public itemAdapter(Context c, String[] i, String[] p, String[] d){
        items=i;
        prices= p;
        descriptions=d;
        mInflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v =mInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.textViewName);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.textViewdescription);
        TextView priceTextView = (TextView) v.findViewById(R.id.textViewPrice);

        String name = items[i];
        String descriptipns = descriptions[i];
        String cost = prices[i];

        nameTextView.setText(name);
        descriptionTextView.setText(descriptipns);
        priceTextView.setText(cost);

        return v;
    }
}
