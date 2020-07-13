package com.example.finaludacity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter  extends ArrayAdapter<Data> {

    public NewsAdapter(Context context, ArrayList<Data> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListView = convertView;

        if (currentListView == null) {
            currentListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list, parent, false);
        }

        com.example.finaludacity.Data newsData = getItem(position);

        TextView titleView = currentListView.findViewById(R.id.title);
        titleView.setText(newsData.getTitle());

        TextView timeView = currentListView.findViewById(R.id.time);
        timeView.setText(newsData.getTime());


        TextView dateView = currentListView.findViewById(R.id.date);
        dateView.setText(newsData.getDate());


        TextView authorView = currentListView.findViewById(R.id.author);
        authorView.setText("~" + newsData.getAuthor());

        TextView sectionNameView = currentListView.findViewById(R.id.sectionName);
        sectionNameView.setText(newsData.getSectionName());

        return currentListView;


    }
}