package com.halit.recyclerviewclickeventsearchviewvolley;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model> main_list;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        /*
        main_list.add(new Model(R.drawable.virat,"Virat Kohli","Batsman"));
        main_list.add(new Model(R.drawable.dhoni,"Dhoni","Wicket-keeper"));
        main_list.add(new Model(R.drawable.dinesh,"Dinesh","Wicket-keeper"));
        main_list.add(new Model(R.drawable.hardik,"Hardik Pandya","Bowler"));
        main_list.add(new Model(R.drawable.jadeja,"Jadeja","All-rounder"));
        main_list.add(new Model(R.drawable.rahane,"Rahane","Batsman"));
        main_list.add(new Model(R.drawable.rohit,"Rohit","Batsman"));
        main_list.add(new Model(R.drawable.shikhar,"Shikhar","Batsman"));
        */

    }


    private void fetchData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://halitpractice.000webhostapp.com/AndroidCoders/playerList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("playerList");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Model model=new Model(object.getString("image"),object.getString("name"),object.getString("role"));
                                main_list.add(model);

                            }
                            adapter=new CustomAdapter(main_list,getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toLowerCase();
                List<Model> myList=new ArrayList<>();
                for (Model model:main_list){
                    String player_name=model.getPlayer_name().toLowerCase();
                    String player_role=model.getPlayer_role().toLowerCase();
                    String image=model.getImage().toLowerCase();

                    if (player_name.contains(newText))
                        myList.add(model);
                    else if (player_role.contains(newText))
                        myList.add(model);
                    else if (image.contains(newText))
                        myList.add(model);
                }
                adapter.setSearchOperation(myList);
                return false;
            }
        });


        return true;
    }


}
