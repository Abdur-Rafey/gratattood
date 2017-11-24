package com.gratattood.gratattood.helper;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.gratattood.gratattood.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafey Sheikh on 10/17/2017.
 */

public class CustomDialogClass extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnBitcoin,btnPaypal,btnCredit;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_box_checkout_screen);
        btnBitcoin = (Button) findViewById(R.id.btnBitcoin);
        btnPaypal = (Button) findViewById(R.id.btnPaypal);
        btnCredit = (Button) findViewById(R.id.btnCredit);

        btnBitcoin.setOnClickListener(this);
        btnPaypal.setOnClickListener(this);
        btnCredit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBitcoin:
                Toast.makeText(getContext(),
                        "Bitcoin",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPaypal:
                Toast.makeText(getContext(),
                        "Paypal",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCredit:
                Toast.makeText(getContext(),
                        "Credit",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }

}