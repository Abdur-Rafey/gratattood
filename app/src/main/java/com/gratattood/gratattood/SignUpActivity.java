package com.gratattood.gratattood;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gratattood.gratattood.app.AppConfig;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.helper.SQLiteHandler;
import com.gratattood.gratattood.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText etName, etEmail, etPassword,etConfirmPassword, etDescription;
    private ImageButton inputImageButton;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    private static final int IMAGE_SIZE = 200;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 12398;
    private Uri outputFileUri;
    private int YOUR_SELECT_PICTURE_REQUEST_CODE = 62335;
    private String userPhotoBase64 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

      //  getSupportActionBar().hide();
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etDescription=(EditText) findViewById(R.id.etDescription);
        inputImageButton = (ImageButton) findViewById(R.id.ibSelectImage);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // Session manager
         session = new SessionManager(getApplicationContext());
    }

    private boolean doValidateFields() {

        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etDescription.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter Description", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etPassword.getText().toString().length() < 8) {
            Toast.makeText(getBaseContext(), "Please enter 8 digit password", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!etConfirmPassword.getText().toString().equalsIgnoreCase(etPassword.getText().toString()) ) {
            Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void bSignUpClicked(View view) {
        if (doValidateFields()) {

            String email = etEmail.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String img = userPhotoBase64;


            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !description.isEmpty()
                    && !img.isEmpty()) {

                registerUser(password, email, name, img, description, "void", "void", "void");

            } else {
                Toast.makeText(getApplicationContext(),
                        "Please enter your details!", Toast.LENGTH_LONG)
                        .show();
            }

        }
    }

    //////////////////////////////////Register User/////////////////////////////////////////////////////////////////
    private void registerUser(final String password, final String email, final String name, final String img,
                              final String description, final String facebook_profile,
                              final String twitter_profile, final String instagram_profile) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();

                params.put("password", password);
                params.put("email", email);
                params.put("name", name);
                params.put("image", img);
                params.put("description", description);
                params.put("facebook_profile", facebook_profile);
                params.put("twitter_profile", twitter_profile);
                params.put("instagram_profile", instagram_profile);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void onSelectImageButtonClick(View view) {
        Log.v(TAG, "open gallery ");
        //ask for storage permissions if not provided yet...
        if (ContextCompat.checkSelfPermission(SignUpActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUpActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            //simply return...
            //if user selects yes, we will call this method in the listener of above request's response
            return;
        } Log.v(TAG, "Permission available ");


        //Code to get image...
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = "asd_" + System.currentTimeMillis() + ".jpg";
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
        Log.e(TAG, "On Activity rezult First Line");
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
                processImage(selectedImageUri);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    private void processImage(Uri imageUri) {
        if (imageUri == null) {
            return;
        }
        Bitmap b = null;
        try {
            b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

            //Now because most mobiles have EXIF issue we will have to rotate the photo based on EXIF value...
            ExifInterface ei = new ExifInterface(imageUri.getPath());
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            Matrix matrix = new Matrix();

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);

                    b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b.getHeight() < 50 || b.getWidth() < 50) {
            Toast.makeText(getApplicationContext(),
                    "Image must atleast be 50x50", Toast.LENGTH_LONG).show();
            return;
        }

        //cropping the image to make a square image...
        int difference = Math.abs(b.getHeight() - b.getWidth());
        if (b.getHeight() > b.getWidth()) {
            b = Bitmap.createBitmap(b, 0, difference / 2, b.getWidth(), b.getWidth());
        } else {
            b = Bitmap.createBitmap(b, difference / 2, 0, b.getHeight(), b.getHeight());
        }

        //scaling down the image to 100x100...
        float ratio = Math.min(
                (float) IMAGE_SIZE / b.getWidth(),
                (float) IMAGE_SIZE / b.getHeight());
        int width = Math.round((float) ratio * b.getWidth());
        int height = Math.round((float) ratio * b.getHeight());

        b = Bitmap.createScaledBitmap(b, width, height, false);

        //Convert to Base 64 and save in variable
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        userPhotoBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        inputImageButton.setBackground(new BitmapDrawable(getResources(), b));
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
         }

}
