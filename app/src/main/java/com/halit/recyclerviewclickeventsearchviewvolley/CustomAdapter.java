package com.halit.recyclerviewclickeventsearchviewvolley;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    List<Model> my_list;
    Context context;

    public CustomAdapter(List<Model> my_list, Context context) {
        this.my_list = my_list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Model model=my_list.get(position);
        holder.name.setText(model.player_name);
        holder.role.setText(model.player_role);
//        holder.image.setImageDrawable(context.getResources().getDrawable(model.getImage()));
        Picasso.with(context).load(model.getImage()).into(holder.image);
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("name",model.getPlayer_name());
                intent.putExtra("role",model.getPlayer_role());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//not recommend
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,role;
        RelativeLayout relative;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            role=itemView.findViewById(R.id.role);
            relative=itemView.findViewById(R.id.relative);


        }
    }

    public void setSearchOperation(List<Model> newList){
        my_list=new ArrayList<>();
        my_list.addAll(newList);
        notifyDataSetChanged();

    }

}
