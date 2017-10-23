package com.gratattood.gratattood;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Rafey Sheikh on 10/17/2017.
 */

public class CustomDialogClass extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button camera,gallery,instagram;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_box);
        camera = (Button) findViewById(R.id.txt_camera);
        gallery = (Button) findViewById(R.id.txt_gallery);
        instagram = (Button) findViewById(R.id.txt_instagram);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        instagram.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_camera:
                Toast.makeText(getContext(),
                        "Camera",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_gallery:
                Toast.makeText(getContext(),
                        "Gallery",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_instagram:
                Toast.makeText(getContext(),
                        "Instagram",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}