package com.gratattood.gratattood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


import static com.gratattood.gratattood.R.id.height;
import static com.gratattood.gratattood.R.id.tattoImage;

public class Design_ExtraINSP_Activity extends AppCompatActivity {
    ImageView img_design, img_Sine, tick_image_Design, backArrow;
    String nameofTattooPictur, nameofBackPictur;
    LinearLayout linear_design;
    String[] colors = {"#F5B7B1", "#EC7063", "#C0392B", "#C39BD3", "#F8C471", "#D35400", "#FDFEFE", "#F7DC6F",
            "#F1C40F", "#D5F5E3", "#58D68D", "#85929E", "#196F3D", "#76D7C4", "#EBDEF0", "#1A5276",
            "#2471A3", "#7FB3D5", "#99A3A4", "#CCD1D1", "#000000", "#ABB2B9"};
    int id = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design__extra_insp_);

        img_design = (ImageView) findViewById(R.id.img_design);
        img_Sine = (ImageView) findViewById(R.id.img_Sine);
        tick_image_Design = (ImageView) findViewById(R.id.tick_image_Design);
        backArrow = (ImageView) findViewById(R.id.backArrow);
        linear_design = (LinearLayout) findViewById(R.id.linear_design);


        Intent intent = getIntent();
        nameofTattooPictur = intent.getStringExtra("NameofPicture");
        nameofBackPictur = intent.getStringExtra("NameofBackground");
        Log.e("Design Activity", "On Activity rezult" + nameofTattooPictur);
        Log.e("Design Activity", "On Activity rezult" + nameofBackPictur);


//        Bitmap b = null;
//        try {
//            b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(nameofBackPictur));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        img_design.setBackground(new BitmapDrawable(getResources(), b));


//        int color = Color.parseColor("#F5B7B1"); //The color u want
//        img_Sine.setColorFilter(color);


        Bitmap icon = BitmapFactory.decodeResource(Design_ExtraINSP_Activity.this.getResources(), R.drawable.wave2);
        img_Sine.setImageBitmap(icon);

        Bitmap icon1 = BitmapFactory.decodeResource(Design_ExtraINSP_Activity.this.getResources(), R.drawable.pic_6);
        img_design.setImageBitmap(icon1);

        fillLayout();

    }

    public void onBackArrowClicked(View view) {
        finish();
    }

    public void ontickClicked(View view) throws IOException {

        ImageView imageView = (ImageView) findViewById(R.id.img_Sine);
        Bitmap bitmap = loadBitmapFromView(imageView);
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root+ "/Gratattood" + "/tattoo_images");
        myDir.mkdirs();
//        String pathTxt = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/" + "temp";
//        File storagePath = new File(pathTxt);
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".png";
        File file = new File(myDir,fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        File from = new File(root+ "/Gratattood" + "/tattoo_images/"+fname);
        File to = new File(root+ "/Gratattood/"+"image.png");
        from.renameTo(to);

        String pathTxt = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/"+"tattoo_images/"+"wave2.png";
        String pathTxt1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Gratattood/"+"tattoo_images/"+"rafey.jpeg";

        Toast.makeText(Design_ExtraINSP_Activity.this, "Going", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Design_ExtraINSP_Activity.this, Design_Save_Activity.class);
        intent.putExtra("NameofPicture", pathTxt);
        intent.putExtra("NameofBackground", pathTxt1);
        startActivity(intent);
    }

    public void fillLayout() {

        for (int i = 0; i < colors.length; i++) {

            // Log.e("Design Activity", "On Activity rezult" + colors[i]);

            final View v = new View(this);
            v.setId(id);
            v.setBackgroundColor(Color.parseColor(colors[i]));

            final int finalI = i;
            linear_design.post(new Runnable() {

                @Override
                public void run() {
//                    Log.v("TEST", "Layout width : " + linear_design.getWidth());
//                    Log.v("TEST", "Layout height : " + linear_design.getHeight());

                    if (finalI == 0 || finalI == colors.length - 1) {
                        double f = linear_design.getWidth() / colors.length;
                      //  Log.v("TEST", "f : " + f);
                        int f1 = linear_design.getWidth() / colors.length;
                      //  Log.v("TEST", "f1 : " + f1 + ", " + colors.length);
                        double f2 = f - f1;
                     //   Log.v("TEST", "f2 : " + f2);
                        double f3 = f2 * 22;

                      //  Log.v("TEST", "f3 : " + f3);
                        double w = linear_design.getWidth() / colors.length + f3;
                        v.setLayoutParams(new LinearLayout.LayoutParams((int) w, linear_design.getHeight()));
                    } else {
                        v.setLayoutParams(new LinearLayout.LayoutParams(linear_design.getWidth() / colors.length, linear_design.getHeight()));
                    }
                }
            });
            final int finalI1 = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img_Sine.setColorFilter(Color.parseColor(colors[finalI1]));
                   // ImageView imageView = <View to save to SD card>;


                }
            });
            linear_design.addView(v);
            id++;
        }

    }

    private Bitmap loadBitmapFromView(View v) {
        final int w = v.getWidth();
        final int h = v.getHeight();
        final Bitmap b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        final Canvas c = new  Canvas(b);
        //v.layout(0, 0, w, h);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
}
