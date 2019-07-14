package com.example.customlistview;

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


public class MyCustomAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<MyItem> mylist;
    public MyCustomAdapter(ArrayList<MyItem> itemArray,Context mContext) {


        super();
        this.mContext = mContext;
        mylist=itemArray;
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
    public void onItemSelected(int position) {
    }
    public class ViewHolder {
        public TextView nametext;
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
            convertView = inflator.inflate(  R.layout.myadapter, null);
            view.nametext = (TextView) convertView.findViewById(R.id.adaptertextview);
            view.tick=(CheckBox)convertView.findViewById(R.id.adaptercheckbox);
            view.tick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag(); // Here
                    // we get  the position that we have set for the checkbox using setTag.
                    mylist.get(getPosition).setChecked(buttonView.isChecked()); // Set the value of checkbox to maintain its state.

                   /* if (isChecked) {
                        //do sometheing here
                    }
                    else
                    {
                        // code here
                    }*/
                }
            });
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.nametext.setText("" + mylist.get(position).getTitle());
        view.tick.setTag(position);
        view.tick.setChecked(mylist.get(position).isChecked());
        return convertView;
    }
}/////// my-transfer app for send large mails