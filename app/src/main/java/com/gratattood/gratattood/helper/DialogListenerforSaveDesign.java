package com.gratattood.gratattood.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.gratattood.gratattood.Design_Activity;
import com.gratattood.gratattood.Design_Save_Activity;
import com.gratattood.gratattood.MainActivity;
import com.gratattood.gratattood.R;
import com.gratattood.gratattood.Tattoo_Activity;

/**
 * Created by abdur on 11/16/2017.
 */

public class DialogListenerforSaveDesign extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnPurchase,btnHome;
    int id;

    public DialogListenerforSaveDesign(Activity a,int id) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.id=id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_box_save_design);
        btnPurchase = (Button) findViewById(R.id.btnPurchase);
        btnHome = (Button) findViewById(R.id.btnHome);

        btnHome.setOnClickListener(this);
        btnPurchase.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHome:
               /* Toast.makeText(getContext(),
                        "Camera",
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(getContext(), MainActivity.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                c.finish();

                break;
            case R.id.btnPurchase:
                //Toast.makeText(getContext(),"Gallery",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getContext(), Tattoo_Activity.class);
                intent1.putExtra("id",id);
              //  intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent1);
               // c.finish();
                break;

            default:
                break;
        }
        dismiss();
    }

}