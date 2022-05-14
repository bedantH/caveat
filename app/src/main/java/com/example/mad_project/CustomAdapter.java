package com.example.mad_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList queryTitle, queryCourse, queryTags, queryMsg, isAnonymous;

    public CustomAdapter(Context context, Activity activity, ArrayList queryTitle, ArrayList queryCourse, ArrayList queryTags, ArrayList queryMsg, ArrayList isAnonymous) {
        this.context = context;
        this.activity = activity;
        this.queryTitle = queryTitle;
        this.queryCourse = queryCourse;
        this.queryTags = queryTags;
        this.queryMsg = queryMsg;
        this.isAnonymous = isAnonymous;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println(queryTitle.get(position).toString());
        System.out.println(queryMsg.get(position).toString());
        holder.queryTitle.setText(queryTitle.get(position).toString());
        holder.queryMsg.setText(queryMsg.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return queryTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView queryTitle, queryMsg;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            queryTitle = itemView.findViewById(R.id.queryText);
            queryMsg = itemView.findViewById(R.id.postedUserText);
            cardView = itemView.findViewById(R.id.myPage);
        }
    }
}
