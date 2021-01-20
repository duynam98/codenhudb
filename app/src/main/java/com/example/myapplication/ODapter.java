package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ODapter extends RecyclerView.Adapter<ODapter.ViewHolder> {

    private Context context;
    private List<Name> nameList;

    public ODapter(Context context) {
        this.context = context;
        nameList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nameList.add(new Name("ODapter: " + i));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_leu_leu, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_leu_leu1, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(nameList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_zone_category);
        }
    }

}
