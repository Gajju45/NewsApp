package com.example.finaludacity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Data>> {

    private NewsAdapter newsAdapter;
    private static final String REQUEST_URL = "http://content.guardianapis.com/search";
    private TextView emptyTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyTextView = findViewById(R.id.empty_text);
        progressBar = findViewById(R.id.progress_bar);


        ConnectivityManager CM = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);


        newsAdapter = new NewsAdapter(this, new ArrayList<Data>());
        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(emptyTextView);
        listView.setAdapter(newsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Data current = newsAdapter.getItem(position);

                Uri news_Uri = Uri.parse(current.getWebURL());

                Intent website = new Intent(Intent.ACTION_VIEW, news_Uri);
                startActivity(website);
            }
        });

        NetworkInfo NI = CM.getActiveNetworkInfo();
        if (NI != null && NI.isConnected()) {
            android.app.LoaderManager loaderManager = getLoaderManager();
            getSupportLoaderManager().initLoader(0, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("There is no internet Connection Please try Again");
        }


    }


    @Override
    public Loader<List<Data>> onCreateLoader(int i, Bundle bundle) {
        Uri base = Uri.parse(REQUEST_URL);

        Uri.Builder uriBuilder = base.buildUpon();

        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "test");

        return new com.example.finaludacity.Loader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Data>> loader, List<Data> tunder) {

        newsAdapter.clear();
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText("No news found!");


        if (tunder != null && !tunder.isEmpty()) {
            newsAdapter.addAll(tunder);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Data>> loader) {

        newsAdapter.clear();
    }


}