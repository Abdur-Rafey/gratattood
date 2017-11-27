package com.gratattood.gratattood;

import android.graphics.Typeface;
import android.os.Build;
import android.provider.FontsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.graphics.Typeface.SERIF;

public class TempTattooActivity extends AppCompatActivity {
    TextView textHeight,txtWidth;
    RadioGroup rg;
    EditText edtText;
    static int x=20;
    RelativeLayout LayTattooSize;
    LinearLayout linearLayoutHeight,linearLayoutWidth;
    View viewDownHeight,viewUpHeight,viewDownWidth,viewUpWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_tattoo);

        textHeight = (TextView) findViewById(R.id.textHeight);
        txtWidth = (TextView) findViewById(R.id.txtWidth);
        viewUpWidth = (View) findViewById(R.id.viewUpWidth);
        viewDownWidth = (View) findViewById(R.id.viewDownWidth);
        viewUpHeight = (View) findViewById(R.id.viewUpHeight);
        viewDownHeight = (View) findViewById(R.id.viewDownHeight);
        edtText = (EditText) findViewById(R.id.edtText);
        LayTattooSize = (RelativeLayout) findViewById(R.id.LayTattooSize);
        linearLayoutHeight = (LinearLayout) findViewById(R.id.linearLayoutHeight);
        linearLayoutWidth = (LinearLayout) findViewById(R.id.linearLayoutWidth);

       // changeTattooSize(500);

        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        changeTattooSize(500);

                        break;
                    case R.id.radio1:
                        changeTattooSize(400);
                        break;

                }
            }
        });
    }

    public void onZoomBtnClicked(View view) {

        edtText.setTextSize(x);
        x=x+10;
    }

    public void onAddTextBtnClicked(View view) {
        edtText.setEnabled(true);
        edtText.setHint("Enter Your Text Here");

    }

    public void onBackArrowClicked(View view) {
        finish();
    }

    public void onTickbtnClicked(View view) {
    }


    public void onCasualBtnClicked(View view) {
        edtText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
    }

    public void onSerifBtnClicked(View view) {
        edtText.setTypeface(Typeface.SERIF);
    }

    public void onMonospaceBtnClicked(View view) {
        edtText.setTypeface(Typeface.MONOSPACE);
    }

    public void onserif_monospaceBtnClicked(View view) {
        edtText.setTypeface(Typeface.SANS_SERIF);
    }
    public void changeTattooSize(int size){

        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) LayTattooSize.getLayoutParams();
        params.width=size;
        params.height=size;
        LayTattooSize.setLayoutParams(params);

        ViewGroup.LayoutParams params1=  linearLayoutWidth.getLayoutParams();
        params1.width=size;
        linearLayoutWidth.setLayoutParams(params1);

        ViewGroup.LayoutParams params2=  linearLayoutHeight.getLayoutParams();
        params2.height=size;
        linearLayoutHeight.setLayoutParams(params2);

    }
}
