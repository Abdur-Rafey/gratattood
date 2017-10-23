package com.gratattood.gratattood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Cart_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);


        ListView yourListView = (ListView) findViewById(R.id.lst_Cart_Activity);

        MyCartListAdapter customAdapter = new MyCartListAdapter(this, R.layout.cart_row, getItemList());

        yourListView .setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);

    }

    public ArrayList<Model_Cart> getItemList()
    {
        ArrayList<Model_Cart> lst = new ArrayList<Model_Cart>();
        for (int i=0;i<10;i++) {

            Model_Cart model_cart = new Model_Cart();

            model_cart.setDesign("Design:");
            model_cart.setComent("Awesome Design");
            model_cart.setQuantity("Quantity:");
            model_cart.setQuantity_number("1");
            model_cart.setPrice("$39.82");
            lst.add(model_cart);
        }
        return lst;
    }
}


