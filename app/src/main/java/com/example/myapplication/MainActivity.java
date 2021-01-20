package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ODapter oDapter = new ODapter(this);
    ImageView imgView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcl_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(oDapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(
                ContextCompat.getDrawable(this, R.drawable.decor));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setOnScrollListener(new CustomScrollListener(linearLayoutManager));
    }
}