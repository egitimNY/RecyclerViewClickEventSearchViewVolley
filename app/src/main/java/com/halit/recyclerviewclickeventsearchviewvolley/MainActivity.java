package com.halit.recyclerviewclickeventsearchviewvolley;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model> main_list;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        fetchData();
        main_list.add(new Model(R.drawable.virat,"Virat Kohli","Batsman"));
        main_list.add(new Model(R.drawable.dhoni,"Dhoni","Wicket-keeper"));
        main_list.add(new Model(R.drawable.dinesh,"Dinesh","Wicket-keeper"));
        main_list.add(new Model(R.drawable.hardik,"Hardik Pandya","Bowler"));
        main_list.add(new Model(R.drawable.jadeja,"Jadeja","All-rounder"));
        main_list.add(new Model(R.drawable.rahane,"Rahane","Batsman"));
        main_list.add(new Model(R.drawable.rohit,"Rohit","Batsman"));
        main_list.add(new Model(R.drawable.shikhar,"Shikhar","Batsman"));

        adapter=new CustomAdapter(main_list,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

/*

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
                                Model model=new Model(object.getInt("image"),object.getString("name"),object.getString("role"));
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
*/

}
