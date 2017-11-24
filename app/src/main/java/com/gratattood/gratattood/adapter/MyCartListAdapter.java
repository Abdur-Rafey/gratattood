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

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.gratattood.gratattood.R;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.model.Model_Cart;

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


            TextView tt2 = (TextView) v.findViewById(R.id.txtComent_Cart);
            TextView tt4 = (TextView) v.findViewById(R.id.txtQuantity_number_Cart);
            TextView tt5 = (TextView) v.findViewById(R.id.txtPrice_cart);
            ImageView im1= (ImageView) v.findViewById(R.id.img_cart);

            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.pic_6);


          if (tt2 != null) {
                tt2.setText(p.getComent());
            }

            if (tt4 != null) {
                tt4.setText(p.getQuantity_number());
            }
            if (tt5 != null) {
                tt5.setText(p.getPrice());
            }


            if (im1 != null) {

                getBitmapFromURLAndSetImage(p.getTattoo(),im1);
                im1.setImageBitmap(icon);
            }



        }

        return v;
    }


    public void getBitmapFromURLAndSetImage(String src, final ImageView iv) {
        ImageRequest ir = new ImageRequest(src,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        iv.setImageBitmap(response);

                    }
                }, 0, 0,null, null);
        AppController.getInstance().addToRequestQueue(ir, "test_image_req");

    }

}