package com.gratattood.gratattood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity_Follow_Feed extends AppCompatActivity {
    private Spinner spinner1;
    ListView listView;
    ArrayList<Model_Follow_Feed> model_follow_feedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__follow__feed);

        ListView yourListView = (ListView) findViewById(R.id.lst_follow_feed_Activity);

// get data from the table by the ListAdapter


        MyListAdapter customAdapter = new MyListAdapter(this, R.layout.follow_feed_lst_row, getItemList());

        yourListView .setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);
        //addListenerOnSpinnerItemSelection();
    }




    /*public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/


public ArrayList<Model_Follow_Feed> getItemList()
{
    ArrayList<Model_Follow_Feed> lst = new ArrayList<Model_Follow_Feed>();
    for (int i=0;i<5;i++) {

        Model_Follow_Feed model_follow_feed = new Model_Follow_Feed();

        model_follow_feed.setUser_name("Abdur Rafey");
        model_follow_feed.setLikes("Liked By 35 others");
        model_follow_feed.setTime("72 hours ago.");

        lst.add(model_follow_feed);
    }
    return lst;
}

}



