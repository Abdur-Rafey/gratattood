package com.gratattood.gratattood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gratattood.gratattood.adapter.ImageAdapter;
import com.gratattood.gratattood.app.AppConfig;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.model.Model_Tattoo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "AppLog/MainActivity";
    private ProgressDialog pDialog;
    private TextView mTextMessage;
    GridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_top);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigation_btm = (BottomNavigationView) findViewById(R.id.navigation_btm);
        navigation_btm.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener1);

        gridView = (GridView) findViewById(R.id.grid_view);
        // Instance of ImageAdapter Class



        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        getTattoos("0", "10000");
    }


    /**
     * function to get all tattoos from server...
     */
    private void getTattoos(final String pageNumber, final String elementsPerPage) {
        // Tag used to cancel the request
        String tag_string_req = "get_all_tattoos";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_ALL_TATTOOS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Get All Tattoo Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);

                    Log.v(TAG, "JSON Response:\n" + jObj);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        JSONArray tattoo = jObj.getJSONArray("message");

                        Model_Tattoo model_tattoo = new Model_Tattoo();
                       ArrayList<Model_Tattoo> lst = new ArrayList<Model_Tattoo>();

                       for (int i=0; i<tattoo.length();i++){

                           JSONObject tattoo_data = tattoo.getJSONObject(i);
                           model_tattoo.setId(Integer.parseInt(tattoo_data.getString("id").toString()));

                           model_tattoo.setTattoo_name(tattoo_data.getString("tattoo_name").toString());
                           model_tattoo.setUser_id(Integer.parseInt(tattoo_data.getString("user_id").toString()));
                           model_tattoo.setWaveform_id(Integer.parseInt(tattoo_data.getString("waveform_id").toString()));
                           model_tattoo.setType(Integer.parseInt(tattoo_data.getString("type").toString()));
                           model_tattoo.setBackground_image(tattoo_data.getString("background_image"));
                           model_tattoo.setPublic_or_private(Integer.parseInt(tattoo_data.getString("public_or_private").toString()));
                           model_tattoo.setTattoo_price(Double.parseDouble(tattoo_data.getString("tattoo_price").toString()));
                           model_tattoo.setCreated_on(tattoo_data.getString("created_on").toString());
                           Log.v(TAG, "tattoo URL" + tattoo_data.getString("background_image"));
                           lst.add(model_tattoo);

                           gridView.setAdapter(new ImageAdapter(MainActivity.this, lst));
                        }   // if no error found, display tattoos..
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
                Log.e(TAG, "Get All Tattoo Error: " + error);
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
                params.put("pagenumber", pageNumber);
                params.put("elementsperpage", elementsPerPage);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    public void onClickPersonalizeBtn(View view) {
       /* Intent intent= new Intent(getBaseContext(), TempTattooActivity.class);
        startActivity(intent);*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Mic_btn:
                    Toast.makeText(MainActivity.this, "Audio",
                            Toast.LENGTH_SHORT).show();
                    Intent intent1= new Intent(getBaseContext(), Record_Audio_Activity.class);
                    startActivity(intent1);
                    return true;
                case R.id.music_btn:
                    Toast.makeText(MainActivity.this, "Music",
                            Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getBaseContext(), LocalAudioGallery_Activity.class);
                    startActivity(intent);
                    return true;
                case R.id.share_btn:
                    Toast.makeText(MainActivity.this, "Share",
                            Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener1
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Toast.makeText(MainActivity.this, "Home",
                            Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getBaseContext(), Activity_Follow_Feed.class);
                    startActivity(intent);
                    return true;
                case R.id.glass:
                    Toast.makeText(MainActivity.this, "Search",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.add:
                    Toast.makeText(MainActivity.this, "Add",
                            Toast.LENGTH_SHORT).show();
                    /* Intent intent= new Intent(getBaseContext(), TempTattooActivity.class);
        startActivity(intent);*/
                    return true;
                case R.id.like:
                    Toast.makeText(MainActivity.this, "Likes",
                            Toast.LENGTH_SHORT).show();
                    Intent intent3= new Intent(getBaseContext(), Activity_Profile.class);
        startActivity(intent3);
                    return true;
                case R.id.profile:
                    Toast.makeText(MainActivity.this, "Profile",
                            Toast.LENGTH_SHORT).show();
                    Intent intent4= new Intent(getBaseContext(), Activity_Profile.class);
                    startActivity(intent4);
                    return true;
            }
            return false;
        }

    };
}
