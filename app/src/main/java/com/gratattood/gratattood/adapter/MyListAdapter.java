package com.gratattood.gratattood.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gratattood.gratattood.helper.CustomOnItemSelectedListener;
import com.gratattood.gratattood.R;
import com.gratattood.gratattood.model.Model_Follow_Feed;

import java.util.ArrayList;

import static com.gratattood.gratattood.R.id.spinner1;

/**
 * Created by Rafey Sheikh on 10/12/2017.
 */

public class MyListAdapter extends ArrayAdapter<Model_Follow_Feed> {


    public MyListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MyListAdapter(Context context, int resource, ArrayList<Model_Follow_Feed> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.follow_feed_lst_row, null);
        }

        Model_Follow_Feed p = getItem(position);

        if (p != null) {
            Spinner spinner= (Spinner) v.findViewById(spinner1);
            TextView tt1 = (TextView) v.findViewById(R.id.txtName_followFeed);
            TextView tt2 = (TextView) v.findViewById(R.id.txtlikesBy_followFeed);
            TextView tt3 = (TextView) v.findViewById(R.id.txtdate_followFeed);
            ImageView im1= (ImageView) v.findViewById(R.id.img_FollowFeed);

            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.pic_9);
            Bitmap icon2 = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.pic_9);

            ImageView im2= (ImageView) v.findViewById(R.id.profile_pic);

            if (tt1 != null) {
                tt1.setText(p.getUser_name());
            }

            if (tt2 != null) {
                tt2.setText(p.getLikes());
            }

            if (tt3 != null) {
                tt3.setText(p.getTime());
            }
            if (im1 != null) {

                im1.setImageBitmap(icon);
            }

            if (im2 != null) {
                im2.setImageBitmap(icon2);
            }
           if(spinner != null)
           {

               spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(p.getUser_name(),getContext()));
           }

        }

        return v;
    }

}