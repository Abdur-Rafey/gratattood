package com.gratattood.gratattood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.gratattood.gratattood.app.AppConfig;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.model.ModelTattooOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tattoo_Activity extends AppCompatActivity {
    private static final String TAG = "TattooActivity";
    String tattooId;
    ImageView backArrow_Tattoo_Activity, profile_pic, imgBackground, img_Sine, imgLike;
    TextView txtTattooName, txtTattooPrice, txtNameUser, txtlikes;
    ModelTattooOrder model_tattoo;
    boolean liked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_);

        CardView card = (CardView) findViewById(R.id.card_view);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));

        txtTattooName = (TextView) findViewById(R.id.txtTattooName);
        txtTattooPrice = (TextView) findViewById(R.id.txtTattooPrice);
        txtNameUser = (TextView) findViewById(R.id.txtNameUser);
        txtlikes = (TextView) findViewById(R.id.txtlikes);
        backArrow_Tattoo_Activity = (ImageView) findViewById(R.id.backArrow_Message_Activity);
        imgBackground = (ImageView) findViewById(R.id.imgBackground);
        img_Sine = (ImageView) findViewById(R.id.img_Sine);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        imgLike = (ImageView) findViewById(R.id.like_pic);

        Intent intent = getIntent();
        tattooId = intent.getStringExtra("NameofPicture");
        tattooId = "10";
        if (tattooId == null) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Error")
                    .setMessage("No Tattoo Find")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }

        getTattoos(tattooId);


    }

    public void onProfileClicked(View view) {
        Intent intent = new Intent(getBaseContext(), Activity_Profile.class);
        // intent.putExtra("model_tattoo",model_tattoo);
        startActivity(intent);
    }

    public void onCartBtnClicked(View view) {
        Intent intent = new Intent(getBaseContext(), Purchase_Activity.class);
        intent.putExtra("model_tattoo", model_tattoo);
        startActivity(intent);
    }

    public void onBackArrowClicked(View view) {

        finish();
    }

    private void getTattoos(final String tattooId) {
        // Tag used to cancel the request
        String tag_string_req = "get_tattoo_detail";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_TATTOO_DETAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //  Log.d(TAG, "Get All Tattoo Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);

                    // Log.v(TAG, "JSON Response:\n" + jObj);
                    boolean error = jObj.getBoolean("error");
                    // Log.v(TAG, "JSON Response:\n" + error);
                    // Check for error node in json
                    if (!error) {
                        JSONObject tattoo_data = jObj.getJSONObject("message");
                        //   Log.v(TAG, "JSON Response:\n" + error);
                        model_tattoo = new ModelTattooOrder();
                        //  ArrayList<ModelTattooOrder> lst = new ArrayList<ModelTattooOrder>();

                        //   Log.v(TAG, "JSON Response:\n" + error);
                        model_tattoo.setId(Integer.parseInt(tattoo_data.getString("id").toString()));
                        model_tattoo.setTattoo_name(tattoo_data.getString("tattoo_name").toString());
                        txtTattooName.setText(tattoo_data.getString("tattoo_name").toString());
                        model_tattoo.setUser_id(Integer.parseInt(tattoo_data.getString("user_id").toString()));
                        model_tattoo.setWaveform_id(Integer.parseInt(tattoo_data.getString("waveform_id").toString()));
                        model_tattoo.setType(Integer.parseInt(tattoo_data.getString("type").toString()));
                        model_tattoo.setBackground_image(tattoo_data.getString("background_image"));
                        getBitmapFromURLAndSetImage(tattoo_data.getString("background_image"), imgBackground);
                        model_tattoo.setPublic_or_private(Integer.parseInt(tattoo_data.getString("public_or_private").toString()));
                        model_tattoo.setTattoo_price(Double.parseDouble(tattoo_data.getString("tattoo_price").toString()));
                        txtTattooPrice.setText("$" + tattoo_data.getString("tattoo_price").toString());
                        model_tattoo.setCreated_on(tattoo_data.getString("created_on").toString());
                        model_tattoo.setComplex_to_print(Integer.parseInt(tattoo_data.getString("complex_to_print").toString()));
                        model_tattoo.setAvailable_to_sale(Integer.parseInt(tattoo_data.getString("available_to_sale").toString()));
                        //    lst.add(model_tattoo);

                        // gridView.setAdapter(new ImageAdapter(MainActivity.this, lst));
                        // if no error found, display tattoos..
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Get All Tattoo Error: " + error);
                if (error instanceof ServerError)
                    Toast.makeText(getApplicationContext(), "Server error occurred, try again", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Couldn't connect to Server, try again", Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                //Log.v(TAG, "mobile: "+ mobile+"\npassword: "+password);
                params.put("id", tattooId);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
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
                }, 0, 0, null, null);
        AppController.getInstance().addToRequestQueue(ir, "test_image_req");

    }

    public void onLiketBtnClicked(View view) {

        if (liked == false) {
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.like);
            imgLike.setImageBitmap(icon);
            liked = true;
        } else {
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.heart_filled);
            imgLike.setImageBitmap(icon);
            liked = false;
        }
    }


}
