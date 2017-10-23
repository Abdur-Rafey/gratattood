package com.gratattood.gratattood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class LocalAudioGallery_Activity extends AppCompatActivity {
    private Spinner spinner1;
    ListView listView;
    ArrayList<Model_Audio_Gallery> model_audio_galleries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_audio_gallery_);

        ListView yourListView = (ListView) findViewById(R.id.lst_local_audio_Activity);

        MyAudioGalleryListAdapter customAdapter = new MyAudioGalleryListAdapter(this, R.layout.audio_gallery_row, getItemList());

        yourListView .setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);
    }




    public ArrayList<Model_Audio_Gallery> getItemList()
    {
        ArrayList<Model_Audio_Gallery> lst = new ArrayList<Model_Audio_Gallery>();
        for (int i=0;i<10;i++) {

            Model_Audio_Gallery model_audio_galleries = new Model_Audio_Gallery();

            model_audio_galleries.setDate("Aug 04");
            model_audio_galleries.setDetail("Fri,04,aug");
            model_audio_galleries.setDuration("00:04");
            model_audio_galleries.setSize("356 KB");
            lst.add(model_audio_galleries);
        }
        return lst;
    }
}
