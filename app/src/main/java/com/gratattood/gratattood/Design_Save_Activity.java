package com.gratattood.gratattood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.gratattood.gratattood.helper.DialogListenerforSaveDesign;

import java.io.File;
import java.io.IOException;

import static com.gratattood.gratattood.R.id.img_design;

public class Design_Save_Activity extends AppCompatActivity {
    String nameofTattooPictur, nameofBackPictur;
    ImageView img_back, img_Sine, img_Sine2;
    EditText edtNameoftattoo;
    String strNameoftattoo;
    Switch switch1;
    boolean privateAudio=false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design__save_);

        img_back = (ImageView) findViewById(img_design);
        img_Sine = (ImageView) findViewById(R.id.imageWave);
        img_Sine2 = (ImageView) findViewById(R.id.imageWave2);
        edtNameoftattoo = (EditText) findViewById(R.id.edtNameoftattoo);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked)
                {
                    privateAudio=isChecked;
                    Toast.makeText(Design_Save_Activity.this, "Your Tattoo will be now Private " +privateAudio, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Design_Save_Activity.this, "Your Tattoo will be now Public " +privateAudio, Toast.LENGTH_SHORT).show();


                }
    }
});

        Intent intent = getIntent();
        nameofTattooPictur = intent.getStringExtra("NameofPicture");
        nameofBackPictur = intent.getStringExtra("NameofBackground");
//        Bitmap icon1 = BitmapFactory.decodeResource(Design_Save_Activity.this.getResources(), R.drawable.pic_6);
//        img_back.setImageBitmap(icon1);


        File imgFile = new File(nameofTattooPictur);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            img_Sine.setImageBitmap(myBitmap);
        }
        File imgFile1 = new File(nameofTattooPictur);
        if (imgFile1.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
            img_Sine2.setImageBitmap(myBitmap);
        }
        File imgFile2 = new File(nameofBackPictur);
        if (imgFile2.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            img_back.setImageBitmap(myBitmap);
        }

    }

    public void ontickClicked(View view) {
        strNameoftattoo = edtNameoftattoo.getText().toString();
        if (strNameoftattoo.isEmpty()) {
            Toast.makeText(Design_Save_Activity.this, "Please Enter the Name  " + strNameoftattoo, Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(Design_Save_Activity.this, "Please Enter the Name  " + strNameoftattoo, Toast.LENGTH_SHORT).show();

            //API will call
            DialogListenerforSaveDesign cdd = new DialogListenerforSaveDesign(Design_Save_Activity.this,10);/////Send id came from Above Api
            cdd.show();
        }
    }
    public void onBackArrowClicked(View view) {

        finish();
    }

}
