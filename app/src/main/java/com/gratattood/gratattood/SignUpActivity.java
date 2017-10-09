package com.gratattood.gratattood;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setActionBar();

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
    }

    private boolean doValidateFields(){

        if (etName.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Please enter name", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etEmail.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etPassword.getText().toString().length() < 8){
            Toast.makeText(getBaseContext(), "Please enter 8 digit password", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void bSignUpClicked(View view) {
        if (doValidateFields()){
            //callAPI
        }
    }
}
