package com.halit.recyclerviewclickeventsearchviewvolley;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView name,role;
    String playername,playerrole;
    int image;
//    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView=findViewById(R.id.image);
        name=findViewById(R.id.name);
        role=findViewById(R.id.role);
        playername=getIntent().getStringExtra("name");
        playerrole=getIntent().getStringExtra("role");
        image=getIntent().getIntExtra("image",0);
        name.setText(playername);
        role.setText(playerrole);
        imageView.setImageResource(image);
//        Picasso.with(this).load(image).into(imageView);


    }
}
