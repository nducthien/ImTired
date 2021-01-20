package com.example.asd.imtired.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asd.imtired.Interface.ItemClickListener;
import com.example.asd.imtired.R;
import com.example.asd.imtired.activity.Start;
import com.example.asd.imtired.adapter.CategoryViewHolder;
import com.example.asd.imtired.common.Common;
import com.example.asd.imtired.model.Categori;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class CategoryFragment extends Fragment {

    private RecyclerView rcvListCategory;
    private FirebaseRecyclerAdapter<Categori, CategoryViewHolder> adapter;
    private DatabaseReference categories;

    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    // Press Ctr + 0 create onCreate and onCreateView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        categories = database.getReference("CategoryBackground");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categori, container, false);

        rcvListCategory = view.findViewById(R.id.listCategory);
        rcvListCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        rcvListCategory.setLayoutManager(layoutManager);

        loadCategories();

        return view;
    }

    private void loadCategories() {

        adapter = new FirebaseRecyclerAdapter<Categori, CategoryViewHolder>(
                Categori.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, Categori model, int position) {
                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onclick(View view, int position, boolean isLongClick) {
                        Intent startGame = new Intent(getActivity(), Start.class);
                        Common.categoryId = adapter.getRef(position).getKey();
                        startActivity(startGame);

                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        rcvListCategory.setAdapter(adapter);
    }
}
