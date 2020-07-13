package com.example.finaludacity;

import android.content.Context;

import java.util.List;

import androidx.loader.content.AsyncTaskLoader;

public class Loader extends AsyncTaskLoader<List<Data>> {

    private String mURL;

    public Loader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Data> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        List<Data> newsDataList = com.example.finaludacity.Querry.fetchNewsData(mURL);
        return newsDataList;
    }
}
