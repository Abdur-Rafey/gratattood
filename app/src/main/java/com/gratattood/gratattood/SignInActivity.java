package com.gratattood.gratattood;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gratattood.gratattood.app.AppConfig;
import com.gratattood.gratattood.app.AppController;
import com.gratattood.gratattood.helper.SQLiteHandler;
import com.gratattood.gratattood.helper.SessionManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = SignInActivity.class.getSimpleName();
    EditText etEmail, etPassword;
    Button bLogIn, bSignUp;
    CheckBox cbRememberMe;
    private ProgressDialog pDialog;
    private SQLiteHandler db;
    private SessionManager session;
    CallbackManager callbackManager;
    LoginButton loginButton;
    TwitterLoginButton loginButton1;
    ProgressDialog progressDialog;
    AccessTokenTracker accessTokenTracker;
    private static final int IMAGE_SIZE = 200;
    private String userPhotoBase64 = null;
    String emailF;
    String id;
    String nameF;
    String emailT;
    String nameT;
    String password ="Qwerty!23456";
    String description="...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("BbrvjYghbSFokclgJT4nws3i5",
                        "HC5I0R0AjX36we3tvL2cbbSZZQ1g2wbM56Bd3cBk96inioCPYx"))
                .debug(true)
                .build();
        Twitter.initialize(config);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signin1);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogIn = (Button) findViewById(R.id.bLogIn);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        cbRememberMe = (CheckBox) findViewById(R.id.cbRememberMe);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton1 = (TwitterLoginButton) findViewById(R.id.login_twitter1);
        // Session manager
        session = new SessionManager(getApplicationContext());

        //////////////////////////////////Twitter/////////////////////////////////////////////////////

        loginButton1.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                         Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(false, false,true);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {


                        if (userResult.data.email != null)
                        {
                     nameT = userResult.data.name;
                     emailT = userResult.data.email;
                    String photoUrlOriginalSize = userResult.data.profileImageUrl.replace("_normal", "");

                    getBitmapFromURL(photoUrlOriginalSize,nameT,emailT);

                        }
                        else
                        {

                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(SignInActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(SignInActivity.this);
                            }
                            builder.setTitle("Login Error")
                                    .setMessage("No Email attached with this account")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }


                    }

                    @Override
                    public void failure(TwitterException exc) {
                        Log.d("TwitterKit", "Verify Credentials Failure", exc);
                    }
                });

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "On failure: " + exception.getMessage());
                exception.printStackTrace();
            }
        });

        //////////////////////////////////////Facebook////////////////////////////////////////////////
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            Log.d(TAG, "onSuccess Response: " + loginResult.getRecentlyGrantedPermissions().toString());

                            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            Log.i(TAG + "onCompleted", response.toString());
                                            // Application code
                                            try {
                                                if (object.has("email"))
                                                {
                                                    emailF= object.getString("email");
                                                    id = object.getString("id");
                                                    String fname = object.getString("first_name");
                                                    String lname = object.getString("last_name");
                                                    nameF =fname +" "+ lname;
                                                    String picPath = "https://graph.facebook.com/" + id + "/picture?width=200&height=150";
                                                    getBitmapFromURL(picPath,nameF,emailF);
                                                }
                                                else
                                                {
                                                  AlertDialog.Builder builder;
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                        builder = new AlertDialog.Builder(SignInActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                                    } else {
                                                        builder = new AlertDialog.Builder(SignInActivity.this);
                                                    }
                                                    builder.setTitle("Login Error")
                                                            .setMessage("No Email attached with this account")
                                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                                            .show();
                                               }

                                                LoginManager.getInstance().logOut();

                                            } catch (JSONException e) {
                                                e.printStackTrace();

                                            }
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,email,first_name,last_name");
                            request.setParameters(parameters);
                            request.executeAsync();


                        }

                        @Override
                        public void onCancel() {
                            // App code
                            Log.d(TAG, "OnCAncel Response: ");
                            Toast.makeText(SignInActivity.this, "Login Cancelled", Toast.LENGTH_LONG);
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                            Log.d(TAG, "Onerror Response: " + exception.getMessage());
                            exception.printStackTrace();
                        }
                    });
       // accessToken = AccessToken.getCurrentAccessToken();
        loginButton.setReadPermissions("email");



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the login button.
        loginButton1.onActivityResult(requestCode, resultCode, data);
    }

    private Boolean doValidateFields() {
        if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter email address", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void onFacebookLoginClick(View view) {

//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

    public void bSignUpClicked(View view) {
        Intent gotoSignUpActivity = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(gotoSignUpActivity);
    }

    public void bLogInClicked(View view) {

        String email_e = etEmail.getText().toString().trim();
        String password_p = etPassword.getText().toString().trim();

        if (doValidateFields()) {
            checkLogin(email_e, password_p);
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
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

                        Toast.makeText(getApplicationContext(),"You Are Login", Toast.LENGTH_LONG).show();
                        checkLogin(email, password);//////Login API
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("message");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
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

            /*@Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("avatar", new DataPart("file_avatar.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mAvatarImage.getDrawable()), "image/jpeg"));
                params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));

                return params;
            }*/

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        session.setLogin(true);

                        JSONObject user = jObj.getJSONObject("message");

                        int uid = Integer.parseInt(user.getString("id").toString());
                        String email = user.getString("email");
                        String description = user.getString("description");
                        String name = user.getString("name");
                        String img = user.getString("image");
                        int user_balance = Integer.parseInt(user.getString("user_balance").toString());


                        // Inserting row in users table
                        db.addUser(uid, email, name, img, user_balance, description);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
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
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        accessTokenTracker.stopTracking();//////Error
    }
    private void processImage(Bitmap bitmap) {

        Bitmap b = bitmap;
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

         }

 public void getBitmapFromURL(String src, final String name, final String email) {
  //   Log.v(TAG + "getBitmapFromURLAndSetImage ", " Enter");
     ImageRequest ir = new ImageRequest(src,
             new Response.Listener<Bitmap>() {
                 @Override
                 public void onResponse(Bitmap response) {
                    Log.v(TAG , "onResponse getBitmapFromURLAndSetImage: ");
                     processImage(response);

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
             }, 0, 0,null, null);
     AppController.getInstance().addToRequestQueue(ir, "test_image_req");

 }

}
