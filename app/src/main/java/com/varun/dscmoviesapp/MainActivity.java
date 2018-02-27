package com.varun.dscmoviesapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

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
                String url = "http://www.omdbapi.com/?s="+searchText+"&apikey=4dc54ff6";

                StringRequest request = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    JSONArray movies = object.getJSONArray("Search");
                                    for (int i = 0; i < movies.length(); i++) {
                                        JSONObject singleMovie = movies.getJSONObject(i);
                                        String movieName = singleMovie.getString("Title");
                                        Toast.makeText(MainActivity.this, movieName, Toast.LENGTH_SHORT).show();
                                        if(i>=10) break;
                                    }
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(request);
                Toast.makeText(MainActivity.this, searchText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
