package com.gratattood.gratattood;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Design_Activity extends AppCompatActivity implements View.OnClickListener {
    public Button ExtraIns;
    ImageView tattoImage, backImage;
    String nameofFile, ImageRetreivePathInDevice;
    private static final int IMAGE_SIZE = 200;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 12398;
    private Uri outputFileUri;
    private int YOUR_SELECT_PICTURE_REQUEST_CODE = 62335;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_);

        tattoImage = (ImageView) findViewById(R.id.tattoImage);
        backImage = (ImageView) findViewById(R.id.backArrow);
        ExtraIns = (Button) findViewById(R.id.extra_Ins);


//        Intent intent = getIntent();
//        nameofFile = intent.getStringExtra("NameofPicture");
//        Bitmap icon = BitmapFactory.decodeResource(Design_Activity.this.getResources(),
//                R.drawable.wave);
//        tattoImage.setImageBitmap(icon);


        ExtraIns.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.extra_Ins:
//                CustomDialogClass cdd=new CustomDialogClass(Design_Activity.this);
//                cdd.show();
                onSelectImageButtonClick();
                break;

            default:
                break;
        }

    }

    public void onSaveBtnClicked(View v) {
        Toast.makeText(Design_Activity.this, "Going", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Design_Activity.this, Design_Save_Activity.class);
        intent.putExtra("NameofPicture", nameofFile);
        startActivity(intent);
    }

    public void onBackArrowClicked(View view) {
        finish();
    }

    public void onSelectImageButtonClick() {
        // Log.v(TAG, "open gallery ");
        //ask for storage permissions if not provided yet...
        if (ContextCompat.checkSelfPermission(Design_Activity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Design_Activity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            //simply return...
            //if user selects yes, we will call this method in the listener of above request's response
            return;
        }// Log.v(TAG, "Permission available ");


        //Code to get image...
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = "asd_" + ".jpg";
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, YOUR_SELECT_PICTURE_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Log.e(TAG, "On Activity rezult First Line");
        if (resultCode == RESULT_OK) {
            if (requestCode == YOUR_SELECT_PICTURE_REQUEST_CODE) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
                    } else {
                        isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                }

                Uri selectedImageUri = null;
                if (isCamera) {
                    selectedImageUri = outputFileUri;
                } else {
                    selectedImageUri = data == null ? null : data.getData();
                }
                Log.e("Design Activity", "On Activity rezult" + selectedImageUri);
//                Bitmap   b = null;
//                try {
//                    b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                tattoImage.setBackground(new BitmapDrawable(getResources(), b));
                // processImage(selectedImageUri);

                Toast.makeText(Design_Activity.this, "Going", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Design_Activity.this, Design_ExtraINSP_Activity.class);
                nameofFile = "wave";
                intent.putExtra("NameofPicture", nameofFile);
                intent.putExtra("NameofBackground", selectedImageUri.toString());
                startActivity(intent);
            }
        }
    }

}
