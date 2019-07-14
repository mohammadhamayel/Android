package com.example.volleyjson;


import android.app.Activity;
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


public class MyCustomAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<MyItem> mylist;
    public MyCustomAdapter(ArrayList<MyItem> itemArray,Context mContext) {
        super();
        this.mContext = mContext;
        mylist=MainActivity.contactList;
    }
    @Override
    public int getCount() {
        return mylist.size();
    }
    @Override
    public String getItem(int position) {
        return mylist.get(position).toString();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder { // this class just to store the values can remove and put parameters instead
        public TextView idtext;
        public TextView titleText;
        public CheckBox tick;
    }
    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view = null;
        LayoutInflater inflator = ((Activity) mContext).getLayoutInflater();
        if (view == null) {
            view = new ViewHolder();
            convertView = inflator.inflate(  R.layout.list_item, null);
            view.idtext = (TextView) convertView.findViewById(R.id.textViewId);
            view.titleText = (TextView) convertView.findViewById(R.id.textViewTitle);
            view.tick=(CheckBox)convertView.findViewById(R.id.checkBoxCompleted);
            view.tick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // here to check if the value of check box is true
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag(); // Here
                    // we get  the position that we have set for the checkbox using setTag.
                    /*mylist.get(getPosition).setCompleted(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                    if (isChecked) {
                        //do sometheing here
                    }
                    else
                    {
                        // code here
                    }*/
                }
            });
            convertView.setTag(view);// do the same for all check boxes in objects
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tick.setTag(position);
        view.idtext.setText("" + mylist.get(position).getUserId());
        view.titleText.setText(mylist.get(position).getTitle());
        view.tick.setText(mylist.get(position).getCompleted());
        view.tick.setChecked(mylist.get(position).isCompleted());
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        MainActivity.contactList.clear();
        if (charText.length() == 0) {
            MainActivity.contactList.addAll(mylist);
        } else {
            for (MyItem wp : mylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    MainActivity.contactList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}