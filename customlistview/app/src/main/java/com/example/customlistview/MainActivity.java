package com.example.customlistview;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView mainactivity;
    // creating arraylist of MyItem type to set to adapter
    ArrayList<MyItem> myitems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainactivity=(ListView)findViewById(R.id.mainactivitylistview);
        //Adding data i.e images and title to be set to adapter to populate listview
        //here i am passing  string to be set as title and boolean as a parameter to MyItem Constructor as our
        //ArrayList is type of MyItem
        myitems.add(new MyItem("First item",true));
        myitems.add(new MyItem("second item",false));
        myitems.add(new MyItem("third item",true));
        myitems.add(new MyItem("fourth item",false));
        myitems.add(new MyItem("fifth item",true));
        myitems.add(new MyItem("sixth item",false));
        myitems.add(new MyItem("seven item",true));
        myitems.add(new MyItem("First item",true));
        myitems.add(new MyItem("second item",false));
        myitems.add(new MyItem("third item",true));
        myitems.add(new MyItem("fourth item",false));
        myitems.add(new MyItem("fifth item",true));
        myitems.add(new MyItem("sixth item",false));
        myitems.add(new MyItem("seven item",true));
        myitems.add(new MyItem("First item",true));
        myitems.add(new MyItem("second item",false));
        myitems.add(new MyItem("third item",true));
        myitems.add(new MyItem("fourth item",false));
        myitems.add(new MyItem("fifth item",true));
        myitems.add(new MyItem("sixth item",false));
        myitems.add(new MyItem("seven item",true));
        //Creating Adapter object for setting to list
        MyCustomAdapter adapter=new MyCustomAdapter(myitems,MainActivity.this);
        mainactivity.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);  //this shows three dots at right corner on click settings open
        return true;
    }
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.adaptercheckbox) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
