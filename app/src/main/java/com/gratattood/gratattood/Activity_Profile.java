package com.gratattood.gratattood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.gratattood.gratattood.adapter.MyProfileAdapter;

public class Activity_Profile extends AppCompatActivity {

    public Integer[] mThumbIds = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_2,
            R.drawable.pic_1, R.drawable.pic_4,
            R.drawable.pic_5
    };
    public Integer[] mThumbIds1 = {

            R.drawable.avatar1, R.drawable.avatar,
            R.drawable.avatar2, R.drawable.com_facebook_auth_dialog_background,
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
                case R.id.created_tab:
                    GridView gridView1 = (GridView) findViewById(R.id.grid_view);
                    // Instance of ImageAdapter Class
                    gridView1.setAdapter(new MyProfileAdapter(Activity_Profile.this,mThumbIds));
                    return true;
                case R.id.likes_tab:
                    GridView gridView2 = (GridView) findViewById(R.id.grid_view);
                    // Instance of ImageAdapter Class
                    gridView2.setAdapter(new MyProfileAdapter(Activity_Profile.this,mThumbIds1));
                    return true;
                case R.id.account_tab:

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_profile);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
}
}