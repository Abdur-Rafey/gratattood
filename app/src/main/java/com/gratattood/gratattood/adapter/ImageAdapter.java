package com.gratattood.gratattood.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.model.Model_Tattoo;

import java.util.ArrayList;

/**
 * Created by Rafey Sheikh on 10/10/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
//    public Integer[] mThumbIds;
    public ArrayList<Model_Tattoo> _data;
 //   public ArrayList<Model_Tattoo> tatto;

    // Constructor
    public ImageAdapter(Context c, ArrayList<Model_Tattoo> tatto){
        mContext = c;
        _data = tatto;
//        this.tatto=new ArrayList<Model_Tattoo>();
//        this.tatto.addAll(_data);
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
         return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ImageView imageView = new ImageView(mContext);
        final Model_Tattoo data = (Model_Tattoo) _data.get(position);
        Log.v( "tattoo URL bk" , data.getBackground_image() + position);
        getBitmapFromURLAndSetImage(data.getBackground_image(), imageView);

//        imageView.setImageResource(_data[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(225, 200));


        return imageView;
    }
    public void getBitmapFromURLAndSetImage(String src, final ImageView iv) {
        //Log.v(TAG + "getBitmapFromURLAndSetImage ", " Enter");
        ImageRequest ir = new ImageRequest(src,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        //Log.v(TAG , "onResponse getBitmapFromURLAndSetImage: ");
                        iv.setImageBitmap(response);

                    }
                }, 0, 0,null, null);
        AppController.getInstance().addToRequestQueue(ir, "test_image_req");

    }
}