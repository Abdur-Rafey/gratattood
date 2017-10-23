package com.gratattood.gratattood;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class SaveAudio_Activity extends AppCompatActivity {
    CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_audio_);

      card  = (CardView) findViewById(R.id.card_view1);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));
    }
}
