package com.example.myfirstapiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnHit;
    ProgressDialog pd;

    listAdapter adapter;

    static ArrayList<Terms> terms =new ArrayList<>();

    Button btnlistFragment, btnCategoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new listAdapter(this,terms);

        btnlistFragment = (Button) findViewById(R.id.buttonListFragment);
        btnCategoryFragment = (Button) findViewById(R.id.buttonCategory);

        new JsonTask().execute("http://173.209.48.194:8040/service1.asmx/GetBanks");


        btnlistFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   new JsonTask().execute("http://173.209.48.194:8040/service1.asmx/GetBanks");
                loadFragment(new listFragment());

            }
        });

        btnCategoryFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new CategoryFragment());
            }
        });


    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }



    private class JsonTask extends AsyncTask<String, String, String> { //

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";


                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
              //      Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                //Log.d("Response from URl",buffer.toString());

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            //                    Log.d("TEST TEST TEST ONPost", result);
            try {

                result = result.substring(75, result.length() - 10);
                JSONArray array = new JSONArray(result);

                //Log.e("test json array ", array.toString());

                for (int i = 0; i < array.length(); i++) {

                    JSONObject row = array.getJSONObject(i);

                    terms.add(new Terms(row.getString("Code"), row.getString("Name"), row.getString("Name2"))); // add the objects to the array list

                }


                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
