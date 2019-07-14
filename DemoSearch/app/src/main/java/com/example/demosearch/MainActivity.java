package com.example.demosearch;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String[] moviewList;
    public static ArrayList<MovieNames> movieNamesArrayList = new ArrayList<MovieNames>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate sample data

        moviewList = new String[]{"Xmen", "Titanic", "Captain America",
                "Iron man", "Rocky", "Transporter", "Lord of the rings", "The jungle book",
                "Tarzan","Cars","Shreck"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        movieNamesArrayList = new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++) {
            MovieNames movieNames = new MovieNames(moviewList[i]);
            // Binds all strings into an array
            movieNamesArrayList.add(movieNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, movieNamesArrayList.get(position).getAnimalName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}