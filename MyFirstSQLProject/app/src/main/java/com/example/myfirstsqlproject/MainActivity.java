package com.example.myfirstsqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    TextView progressTextView;
    Map<String,Double> fruitsMap = new LinkedHashMap<String, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        progressTextView = findViewById(R.id.textViewProgress);
        thisContext= this;

        progressTextView.setText("");
        Button btn = findViewById(R.id.buttonGetData);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData retrieveData = new GetData();
                retrieveData.execute("");
            }
        });
    }

    private class GetData extends AsyncTask<String,String,String > {

        String msg= "";
        //JDBC Driver Name and Database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        //Example: 192.168.1.10:3306
        static final String DB_URL = "jdbc:mysql://"+DbStrings.DATABASE_URL + "/" + DbStrings.DATABASE_Name;

        @Override
        protected void onPreExecute(){
            progressTextView.setText("Connecting to database...");
        }


        protected String doInBackground(String... strings) {

            Connection conn = null;
            Statement stmt = null;

            try {


                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL,DbStrings.USERNAME,DbStrings.PASSWORD);


                stmt= conn.createStatement();
                /*String sql="SELECT * FROM fruits";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    fruitsMap.put(name,price);
                }*/

                msg = "process Complete";

                //rs.close();stmt.close();conn.close();


            }catch (SQLException connError){
                msg = "An exception was thrown for Connection.";
                msg = DB_URL;
                connError.printStackTrace();
            }catch (ClassNotFoundException e){
                msg = "A Class Not Found Exception was Thrown";
                e.printStackTrace();
            }finally {
                try{
                    if(stmt != null)
                        stmt.close();
                }catch (SQLException r){
                    r.printStackTrace();
                }

                try{
                    if(conn != null)
                        conn.close();
                }catch (SQLException e ){
                    e.printStackTrace();
                }

                //msg = "db finally ";

            }

            return null;
        }

        @Override
        protected void onPostExecute(String msg){

            progressTextView.setText(this.msg);

            if (fruitsMap.size() >0){
                itemAdapter = new ItemAdapter(thisContext, fruitsMap);
                myListView.setAdapter(itemAdapter);
            }
        }
    }

}//End Of MainActivity
