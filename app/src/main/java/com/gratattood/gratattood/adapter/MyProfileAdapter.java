package com.gratattood.gratattood.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.gratattood.gratattood.app.AppController;

import java.util.ArrayList;

/**
 * Created by abdur on 10/31/2017.
 */

public class MyProfileAdapter extends BaseAdapter {
    private Context mContext;
        public Integer[] mThumbIds;


    // Constructor
    public MyProfileAdapter(Context c, Integer[] imges){
        mContext = c;
        mThumbIds=imges;

    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(225, 200));


        return imageView;
    }


    }
