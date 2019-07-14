package com.example.volleyjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import java.util.ArrayList;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener{

    // json array response url
    private String urlJsonArry = "https://jsonplaceholder.typicode.com/todos";

    private static String TAG = MainActivity.class.getSimpleName();  //-------------- change the list view to my custom view and work on one class ------------------
    private Button btnMakeArrayRequest;

    // Progress dialog
    private ProgressDialog pDialog;

    // temporary string to show the parsed response
  //  private ListView lv; //changed to listview instead of textview

    //ArrayList<HashMap<String, String>> contactList;
    static ArrayList<MyItem> contactList;


    // Declare Variables
    private ListView list;
    private ListViewAdapter adapterSearch; // changed ListViewAdapter to MyCustomAdapter
    private SearchView editsearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
      //  lv = (ListView) findViewById(R.id.list);
        contactList = new ArrayList<>();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.listItem);
        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json array request

                makeJsonArrayRequest();

            }
        });





    }
// display items in the list
    public class ViewHolder {
        public TextView nametext;
        public CheckBox tick;
    }


    /**
     * Method to make json array request where response starts with [
     * */
    private void makeJsonArrayRequest() {


        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String userId = person.getString("userId");
                                String id = person.getString("id");
                                String title = person.getString("title");
                                boolean completed = ("true"== person.getString("completed"));



                                MyItem myItem = new MyItem(userId,id,title,completed);

                                //Log.e("items print",""+userId +" "+ id + " " + title+" "+ completed);

                                contactList.add(myItem);

                            }


                            adapterSearch = new ListViewAdapter(MainActivity.this); //before move outside change list view to my custom-view class


                            // Binds the Adapter to the ListView ------------------------------ move this outside the method
                            list.setAdapter(adapterSearch);


                            // Locate the EditText in listview_main.xml
                            editsearch = (SearchView) findViewById(R.id.searchView);
                            editsearch.setOnQueryTextListener(MainActivity.this);

                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Toast.makeText(MainActivity.this, contactList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                   // Log.e("check running", " inner text heeeeeeeeeeeeereeeeeeeeeeeeeeeeeee");

                                }
                            });

                            // add item list to the mail activity by the adapter

                            MyCustomAdapter adapter=new MyCustomAdapter(contactList,MainActivity.this);
                    //        lv.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapterSearch.filter(text);
        return false;
    }


}