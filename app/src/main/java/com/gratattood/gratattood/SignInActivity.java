package com.gratattood.gratattood;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button bLogIn, bSignUp;
    CheckBox cbRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setActionBar();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogIn = (Button) findViewById(R.id.bLogIn);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        cbRememberMe = (CheckBox) findViewById(R.id.cbRememberMe);

    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign In");
    }

    private Boolean doValidateFields(){
        if (etEmail.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"Please enter email address",Toast.LENGTH_LONG).show();
            return false;
        }
        if (etPassword.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"Please enter password",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void startNextActivity(){

    }

    public void bSignUpClicked(View view) {
        Intent gotoSignUpActivity = new Intent(getBaseContext(),SignUpActivity.class);
        startActivity(gotoSignUpActivity);
    }

    public void bLogInClicked(View view) {
        if (doValidateFields()){
            Intent gotoMainActivity = new Intent(getBaseContext(),MainActivity.class);
            startActivity(gotoMainActivity);
        }
    }
}
