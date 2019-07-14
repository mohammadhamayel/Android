                package com.example.dataset.recycleviewjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
/*
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final  String urlData = "http://173.209.48.194:8040/service1.asmx/GetAllCustomers";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        for (int i = 0;i<=10; i++){
            ListItem listItem = new ListItem(
                    "Code" + i+1,
                    "eeeeg"
            );
            listItems.add(listItem);
        }
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
       // loadRecycleViewData();
    }
    /*
private void loadRecycleViewData(){
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading data...");
    progressDialog.show();
    StringRequest stringRequest = new StringRequest(Request.Method.GET,
            urlData,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("Customer");

                        for(int i = 0; i<array.length(); i++){
                            JSONObject o = array.getJSONObject(i);
                            ListItem item = new ListItem(
                                    o.getString("Code"),
                                    o.getString("Name")

                            );
                            listItems.add(item);
                        }
                        adapter = new MyAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
    );
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);


}
*/
}
