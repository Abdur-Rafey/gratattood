package com.gratattood.gratattood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gratattood.gratattood.app.AppConfig;
import com.gratattood.gratattood.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "AppLog/MainActivity";
    private ProgressDialog pDialog;
    private TextView mTextMessage;
    public Integer[] mThumbIds = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_2,
            R.drawable.pic_1, R.drawable.pic_4,
            R.drawable.pic_5,
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_2,
            R.drawable.pic_1, R.drawable.pic_4,
            R.drawable.pic_5
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Mic_btn:
                    Toast.makeText(MainActivity.this, "Audio",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.music_btn:
                    Toast.makeText(MainActivity.this, "Music",
                            Toast.LENGTH_SHORT).show();
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
                    return true;
                case R.id.glass:
                    Toast.makeText(MainActivity.this, "Search",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.add:
                    Toast.makeText(MainActivity.this, "Add",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.like:
                    Toast.makeText(MainActivity.this, "Likes",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.profile:
                    Toast.makeText(MainActivity.this, "Profile",
                            Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_top);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navigation_btm = (BottomNavigationView) findViewById(R.id.navigation_btm);
        navigation_btm.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener1);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this, mThumbIds));


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        getTattoos("1", "10");
    }


    /**
     * function to get all tattoos from server...
     */
    private void getTattoos(final String pageNumber, final String elementsPerPage) {
        // Tag used to cancel the request
        String tag_string_req = "get_all_tattoos";

//        pDialog.setMessage("Logging in ...");
//        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_ALL_TATTOOS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Get All Tattoo Response: " + response.toString());
//                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);

                    Log.v(TAG, "JSON Response:\n" + jObj);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
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
                Log.e(TAG, "Get All Tattoo Error: " + error);
                if (error instanceof ServerError)
                    Toast.makeText(getApplicationContext(), "Server error occurred, try again", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Couldn't connect to Server, try again", Toast.LENGTH_LONG).show();
//                hideDialog();
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
}
