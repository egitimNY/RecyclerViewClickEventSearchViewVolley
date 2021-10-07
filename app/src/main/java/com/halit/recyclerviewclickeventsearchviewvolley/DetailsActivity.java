package com.halit.recyclerviewclickeventsearchviewvolley;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name,role;
    String playername,playerrole;
    String image;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView=findViewById(R.id.image);
        name=findViewById(R.id.name);
        role=findViewById(R.id.role);
        playername=getIntent().getStringExtra("name");
        playerrole=getIntent().getStringExtra("role");
        image=getIntent().getStringExtra("image");
        name.setText(playername);
        role.setText(playerrole);
//        imageView.setImageResource(image);  // local drawable den image leri almak gerekirse picasso yerine bunu kullaniyoruz
        Picasso.with(this).load(image).into(imageView);
    }
}
