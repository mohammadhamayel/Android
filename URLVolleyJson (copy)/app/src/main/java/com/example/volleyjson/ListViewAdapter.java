package com.example.volleyjson;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<MyItem> arraylist;

    public ListViewAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<MyItem>();
        this.arraylist.addAll(MainActivity.contactList); // changed all movieNamesArrayList to contactList
    }

    public class ViewHolder {
        TextView name;
        TextView title;
        CheckBox comp;
    }

    @Override
    public int getCount() {
        return MainActivity.contactList.size();
    }

    @Override
    public MyItem getItem(int position) {
        return MainActivity.contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);// here used the list component for display
            // Locate the TextViews in listview_item.xml


            holder.name = (TextView) view.findViewById(R.id.textViewId);
            holder.title = (TextView) view.findViewById(R.id.textViewTitle);
            holder.comp = (CheckBox) view.findViewById(R.id.checkBoxCompleted);
            holder.comp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // here to check if the value of check box is true
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag(); // Here
                    // we get  the position that we have set for the checkbox using setTag.
                    arraylist.get(getPosition).setCompleted(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                    if (isChecked) {
                        //do sometheing here
                    }
                    else
                    {
                        // code here
                    }
                }
            });
            view.setTag(holder);


        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

        holder.comp.setTag(position); // this used to identify the check box

        holder.name.setText(MainActivity.contactList.get(position).getId());
        holder.title.setText(MainActivity.contactList.get(position).getTitle());
        holder.comp.setText(MainActivity.contactList.get(position).getCompleted());
        holder.comp.setChecked(MainActivity.contactList.get(position).isCompleted());

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        MainActivity.contactList.clear();
        if (charText.length() == 0) {
            MainActivity.contactList.addAll(arraylist);
        } else {
            for (MyItem wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    MainActivity.contactList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}