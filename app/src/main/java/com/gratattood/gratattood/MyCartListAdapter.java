package com.gratattood.gratattood;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rafey Sheikh on 10/17/2017.
 */

public class MyCartListAdapter extends ArrayAdapter<Model_Cart> {


    public MyCartListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MyCartListAdapter(Context context, int resource, ArrayList<Model_Cart> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cart_row, null);
        }

        Model_Cart p = getItem(position);

        if (p != null) {

            TextView tt1 = (TextView) v.findViewById(R.id.txtdesign_Cart);
            TextView tt2 = (TextView) v.findViewById(R.id.txtComent_Cart);
            TextView tt3 = (TextView) v.findViewById(R.id.txtQuantity_Cart);
            TextView tt4 = (TextView) v.findViewById(R.id.txtQuantity_number_Cart);
            TextView tt5 = (TextView) v.findViewById(R.id.txtPrice_cart);
            ImageView im1= (ImageView) v.findViewById(R.id.img_cart);

            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.pic_6);




            if (tt1 != null) {
                tt1.setText(p.getDesign());
            }

            if (tt2 != null) {
                tt2.setText(p.getComent());
            }

            if (tt3 != null) {
                tt3.setText(p.getQuantity());
            }

            if (tt4 != null) {
                tt4.setText(p.getQuantity_number());
            }
            if (tt5 != null) {
                tt5.setText(p.getPrice());
            }


            if (im1 != null) {

                im1.setImageBitmap(icon);
            }



        }

        return v;
    }

}