package com.example.myfirstapiproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Terms> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {// just call the layout variables in object

        TextView textViewCode;
        TextView textViewName;
        TextView textViewName2;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewCode = (TextView) itemView.findViewById(R.id.textViewCode);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewName2 = (TextView) itemView.findViewById(R.id.textViewName2);

           // this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<Terms> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewCode = holder.textViewCode;
        TextView textViewName = holder.textViewName;
        TextView textViewName2 = holder.textViewName2;

        ImageView imageView = holder.imageViewIcon;

        textViewCode.setText(dataSet.get(listPosition).getCode());
        textViewName.setText(dataSet.get(listPosition).getName());
        textViewName2.setText(dataSet.get(listPosition).getName2());
      //  imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}