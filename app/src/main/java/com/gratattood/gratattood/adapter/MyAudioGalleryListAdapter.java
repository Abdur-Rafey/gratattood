package com.gratattood.gratattood.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gratattood.gratattood.helper.CustomOnItemSelectedListener;
import com.gratattood.gratattood.R;
import com.gratattood.gratattood.helper.NDSpinner;
import com.gratattood.gratattood.model.Model_Audio_Gallery;

import java.util.ArrayList;

/**
 * Created by Rafey Sheikh on 10/17/2017.
 */

public class MyAudioGalleryListAdapter extends ArrayAdapter<Model_Audio_Gallery> {

    public MyAudioGalleryListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MyAudioGalleryListAdapter(Context context, int resource, ArrayList<Model_Audio_Gallery> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.audio_gallery_row, null);
        }

        final Model_Audio_Gallery p = getItem(position);

        if (p != null) {
            final NDSpinner spinner = v.findViewById(R.id.spinner_AudioGallery);
//            spinner.setSelection(position, false);
            TextView tt1 = (TextView) v.findViewById(R.id.txtname_AudioGallery);
            TextView tt2 = (TextView) v.findViewById(R.id.txtdetail_AudioGallery);
            TextView tt3 = (TextView) v.findViewById(R.id.txtDuration_AudioGallery);
            TextView tt4 = (TextView) v.findViewById(R.id.txtSize_AudioGallery);
            ImageView im1 = (ImageView) v.findViewById(R.id.AduGal_Pic);

            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.pic_6);


            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getDetail());
            }

            if (tt3 != null) {

                            tt3.setText(p.getDuration());
            }

            if (tt4 != null) {
                tt4.setText(p.getSize());
            }

            if (im1 != null) {

                im1.setImageBitmap(icon);
            }

            if (spinner != null) {

                spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener(p.getName(),getContext()));

            }
        }
        return v;
    }

}