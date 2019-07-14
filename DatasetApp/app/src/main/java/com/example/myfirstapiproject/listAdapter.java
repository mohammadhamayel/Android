package com.example.myfirstapiproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Terms> terms;

    public listAdapter(Context context, ArrayList<Terms> terms){
        this.context = context;
        this.terms = terms;
    }

    @Override
    public int getCount() {
        return terms.size();
    }

    @Override
    public Object getItem(int i) {
        return terms.get(i).toString();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder {
        TextView code;
        TextView name;
        TextView name2;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder view = null;
        LayoutInflater inflator = ((Activity) context).getLayoutInflater();
        if (view == null) {
            view = new ViewHolder();
            convertView = inflator.inflate(  R.layout.fragment_data, null);
            view.code = (TextView) convertView.findViewById(R.id.textViewCode);
            view.name=(TextView) convertView.findViewById(R.id.textViewName);
            view.name2=(TextView) convertView.findViewById(R.id.textViewName2);

            convertView.setTag(view);

        }else {
            view = (ViewHolder) convertView.getTag();
        }

        view.code.setText(terms.get(i).getCode());
        view.name.setText(terms.get(i).getName());
        view.name2.setText(terms.get(i).getName2());


        return convertView;
    }

}
