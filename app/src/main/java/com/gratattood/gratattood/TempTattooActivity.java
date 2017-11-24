package com.gratattood.gratattood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TempTattooActivity extends AppCompatActivity {
    TextView txtserif_monospace,txtmonospace,txtsans_serif_condensed,txtcasual;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_tattoo);

        txtserif_monospace = (TextView) findViewById(R.id.txtserif_monospace);
        txtmonospace = (TextView) findViewById(R.id.txtmonospace);
        txtsans_serif_condensed = (TextView) findViewById(R.id.txtsans_serif_condensed);
        txtcasual = (TextView) findViewById(R.id.txtcasual);


        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                       // modelTattooOrder.setSize(false);
                        break;
                    case R.id.radio1:
                      //  modelTattooOrder.setSize(true);
                        break;

                }
            }
        });
    }

    public void onZoomBtnClicked(View view) {
    }

    public void onAddTextBtnClicked(View view) {

    }

    public void onBackArrowClicked(View view) {
    }

    public void onTickbtnClicked(View view) {
    }
}
