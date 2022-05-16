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

public class CustomAdapterSuggestion extends RecyclerView.Adapter<CustomAdapterSuggestion.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList suggestionTitle, suggestionCourse, suggestionTags, suggestionMsg, isAnonymous;

    public CustomAdapterSuggestion(Context context, Activity activity, ArrayList suggestionTitle, ArrayList suggestionCourse, ArrayList suggestionTags, ArrayList suggestionMsg, ArrayList isAnonymous) {
        this.context = context;
        this.activity = activity;
        this.suggestionTitle = suggestionTitle;
        this.suggestionCourse = suggestionCourse;
        this.suggestionTags = suggestionTags;
        this.suggestionMsg = suggestionMsg;
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
        System.out.println(suggestionTitle.get(position).toString());
        System.out.println(suggestionMsg.get(position).toString());
        holder.suggestionTitle.setText(suggestionTitle.get(position).toString());
        holder.suggestionMsg.setText(suggestionMsg.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return suggestionTitle.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView suggestionTitle, suggestionMsg;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            suggestionTitle = itemView.findViewById(R.id.postedUserText);
            suggestionMsg = itemView.findViewById(R.id.queryText);
            cardView = itemView.findViewById(R.id.myPage);
        }
    }
}
