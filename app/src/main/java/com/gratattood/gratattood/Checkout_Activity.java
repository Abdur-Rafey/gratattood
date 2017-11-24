package com.gratattood.gratattood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gratattood.gratattood.helper.CustomDialogClass;
import com.gratattood.gratattood.helper.NDSpinner;
import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

public class Checkout_Activity extends AppCompatActivity  {
    NDSpinner spinnerShipMethods,spinnerCountry;
    TextView txtShipCost,txtShipDiscount,txtMessage;
    String country,shipMethd;
    boolean countrySelected = false, shipMethodSelected=false;
    boolean firstTime=true;
    boolean firstTime2=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_);

        spinnerShipMethods = (NDSpinner) findViewById(R.id.spinnerShipMethods);
        spinnerCountry = (NDSpinner) findViewById(R.id.spinnerCountry);
        txtShipCost = (TextView) findViewById(R.id.txtShipCost);
        txtShipDiscount = (TextView) findViewById(R.id.txtShipDiscount);
        txtMessage = (TextView) findViewById(R.id.txtMessage);

        ////////////////////////////////////////Get Ship Method////////////////////////////////////////////////
        spinnerShipMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                if (firstTime2)
                {
                    firstTime2=false;
                    return;
                }
                if (position==0)
                {
                    Toast.makeText(parent.getContext(),"Please Select the Shipping Method",Toast.LENGTH_SHORT).show();

                }
                if (position==1)
                {
                    txtMessage.setText("Priority Mehthod will take 1-4 days for Shipping");
                    shipMethd=parent.getItemAtPosition(position).toString();
                    shipMethodSelected=true;
                }
                if (position==2)
                {
                    txtMessage.setText("Standard Mehthod will take 5-14 days for Shipping");
                    shipMethd=parent.getItemAtPosition(position).toString();
                    shipMethodSelected=true;

                }
                if (position==3)
                {
                    txtMessage.setText("Free Shipping Mehthod will take 5-14 days for Shipping");
                    shipMethd=parent.getItemAtPosition(position).toString();
                    shipMethodSelected=true;

                }
//
//                Log.v("CustomOnItemSLTAG", "Item selected: "+position);
//                Log.v("item", (String) parent.getItemAtPosition(position));
//                Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
//                country=parent.getItemAtPosition(position).toString();
//                shipMethodSelected=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                Toast.makeText(parent.getContext(),"Please Select Country",Toast.LENGTH_SHORT).show();
                shipMethodSelected=false;
            }
        });
        //////////////////////////////////Get Country//////////////////////////////////////////////////////////
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                if (firstTime)
                {
                    firstTime=false;
                    return;
                }
                if (position==0)
                {
                    Toast.makeText(parent.getContext(),"Please Select the Country",Toast.LENGTH_SHORT).show();
                    countrySelected=false;
                }
                else
                {
                Log.v("CustomOnItemSLTAG", "Item selected: "+position);
                Log.v("item", (String) parent.getItemAtPosition(position));
                Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                country=parent.getItemAtPosition(position).toString();
                countrySelected=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                Toast.makeText(parent.getContext(),"Please Select Shipping Method",Toast.LENGTH_SHORT).show();
                countrySelected=false;
            }
        });

    }

    public void onNextBtnClicked(View view)
    {

    }

    public void onOtherBtnClicked(View view)
    {
        CustomDialogClass cdd=new CustomDialogClass(Checkout_Activity.this);
        cdd.show();
    }

}
