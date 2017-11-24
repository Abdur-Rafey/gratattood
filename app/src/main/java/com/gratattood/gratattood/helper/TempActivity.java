package com.gratattood.gratattood.helper;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.gratattood.gratattood.R;
import com.gratattood.gratattood.app.AppController;

public class TempActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

      //  iv = (ImageView) findViewById(R.id.iv);

        ImageRequest ir = new ImageRequest("https://www.google.com.pk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                       // iv.setImageBitmap(response);
                    }
                }, 0, 0,
                ImageView.ScaleType.FIT_CENTER,
                null, null);
        AppController.getInstance().addToRequestQueue(ir, "test_image_req");



    }
}
