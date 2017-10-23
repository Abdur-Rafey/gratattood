package com.gratattood.gratattood;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

public class Purchase_Activity extends AppCompatActivity implements HorizontalNumberPickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_);
        CardView card = (CardView) findViewById(R.id.view2);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));

        HorizontalNumberPicker horizontalNumberPicker2 =(HorizontalNumberPicker) findViewById(R.id.horizontal_number_picker2) ;

        horizontalNumberPicker2.getButtonMinusView().setText("<");
        horizontalNumberPicker2.getButtonPlusView().setText(">");
        horizontalNumberPicker2.setShowLeadingZeros(true);
        horizontalNumberPicker2.setValue(23);


        horizontalNumberPicker2.setListener(this);
    }


    @Override
        public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker,int value) {
            switch (horizontalNumberPicker.getId()) {


                case R.id.horizontal_number_picker2:
                    DebugLog.d("horizontal_number_picker2 current value: " + value);
                    break;

            }
        }

}
