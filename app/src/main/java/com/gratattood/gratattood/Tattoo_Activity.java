package com.gratattood.gratattood;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class Tattoo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_);

        CardView card = (CardView) findViewById(R.id.card_view);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));
    }
}
