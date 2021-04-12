package com.demo.moviedbapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder>
{
    private Context mContext;
    private List<MovieModelClass> mData;

    public Adaptery(Context context, List<MovieModelClass> mData) {
        this.mData = mData;
        this.mContext = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.movie_item , parent , false);

        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        holder.id_rating.setText(mData.get(position).getId_rating());
        holder.name.setText(mData.get(position).getName());

        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImg())
                .into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i1 = new Intent(mContext, MovieDetails.class);
                i1.putExtra("id",mData.get(position).getCard_id());
                mContext.startActivity(i1);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView id_rating;
        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            id_rating = itemView.findViewById(R.id.id_rating);
            name = itemView.findViewById(R.id.name_text);
            img = itemView.findViewById(R.id.img_view);
        }
    }



}
