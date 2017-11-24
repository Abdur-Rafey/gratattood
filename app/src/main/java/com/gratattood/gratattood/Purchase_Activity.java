package com.gratattood.gratattood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gratattood.gratattood.model.Cart;
import com.gratattood.gratattood.model.ModelTattooOrder;
import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

public class Purchase_Activity extends AppCompatActivity implements HorizontalNumberPickerListener {
    ModelTattooOrder modelTattooOrder;
    TextView txtPricePurchase;
    RadioGroup rg;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_);

        CardView card = (CardView) findViewById(R.id.view2);
        card.setCardBackgroundColor(Color.parseColor("#f1b10e"));


        modelTattooOrder = new ModelTattooOrder();
        modelTattooOrder = (ModelTattooOrder) getIntent().getSerializableExtra("model_tattoo");
        modelTattooOrder.setSize(false);
        modelTattooOrder.setQuantity(0);

        txtPricePurchase = (TextView) findViewById(R.id.txtPricePurchase);
        txtPricePurchase.setText("$" + modelTattooOrder.getTattoo_price() + "");
        cart = new Cart();

        HorizontalNumberPicker horizontalNumberPicker2 = (HorizontalNumberPicker) findViewById(R.id.horizontal_number_picker2);
        horizontalNumberPicker2.getButtonMinusView().setText("<");
        horizontalNumberPicker2.getButtonPlusView().setText(">");
        horizontalNumberPicker2.setValue(0);


        horizontalNumberPicker2.setListener(this);

        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        modelTattooOrder.setSize(false);
                        break;
                    case R.id.radio1:
                        modelTattooOrder.setSize(true);
                        break;

                }
            }
        });
    }


    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {
        switch (horizontalNumberPicker.getId()) {
            case R.id.horizontal_number_picker2:
                modelTattooOrder.setQuantity(value);
                break;

        }
    }


    public void onCartBtnClicked(View view) {
        if (modelTattooOrder.getQuantity() != 0) {
            cart.addTattooOrderToCart(modelTattooOrder);
            Intent intent = new Intent(getBaseContext(), Cart_Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please Select the Quantity", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackArrowClicked(View view) {

        finish();
    }

}
