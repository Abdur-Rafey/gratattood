package com.gratattood.gratattood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        gridView.setAdapter(new ImageAdapter(this,mThumbIds));
    }
    }
