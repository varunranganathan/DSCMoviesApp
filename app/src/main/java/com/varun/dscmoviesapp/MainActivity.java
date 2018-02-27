package com.varun.dscmoviesapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchBox = (EditText) findViewById(R.id.searchBar);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        ListView listView = (ListView) findViewById(R.id.moviesList);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchBox.getEditableText().toString();
                Toast.makeText(MainActivity.this, searchText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
