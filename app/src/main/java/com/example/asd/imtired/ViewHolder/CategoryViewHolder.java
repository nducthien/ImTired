package com.example.asd.imtired.ViewHolder;

// we need create CategoryViewHolder to process each item at Recycler Adapter

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asd.imtired.Interface.ItemClickListener;
import com.example.asd.imtired.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView category_name;
    public ImageView category_image;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        category_image = itemView.findViewById(R.id.category_image);
        category_name = itemView.findViewById(R.id.category_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onclick(v, getAdapterPosition(), false);

    }
}
