package com.gratattood.gratattood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

public class Checkout_Activity extends AppCompatActivity implements HorizontalNumberPickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_);

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
