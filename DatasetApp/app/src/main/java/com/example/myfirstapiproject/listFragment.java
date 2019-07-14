package com.example.myfirstapiproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;



public class listFragment extends Fragment {


    View view;

    listAdapter adapter;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false); // need to call the list here and made the inflator here
// get the reference of TextViews
        /*code = (TextView) view.findViewById(R.id.textViewCode);
        name = (TextView) view.findViewById(R.id.textViewName);
        name2 = (TextView) view.findViewById(R.id.textViewName2);
        */

        listView = (ListView) view.findViewById(R.id.list);

        adapter = new listAdapter(getActivity(),MainActivity.terms);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();// this to restrict changes on the list from UI, not crash when moving it by mouse
        return view;
    }
}