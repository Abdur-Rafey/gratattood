package com.gratattood.gratattood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Design_Activity extends AppCompatActivity implements View.OnClickListener {
    public Button ExtraIns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_);

        ExtraIns = (Button) findViewById(R.id.extra_Ins);
        ExtraIns.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.extra_Ins:
                CustomDialogClass cdd=new CustomDialogClass(Design_Activity.this);
                cdd.show();
                break;

            default:
                break;
        }

    }


}
