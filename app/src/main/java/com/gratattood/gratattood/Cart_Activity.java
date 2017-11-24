package com.gratattood.gratattood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gratattood.gratattood.adapter.MyCartListAdapter;
import com.gratattood.gratattood.model.Cart;
import com.gratattood.gratattood.model.ModelTattooOrder;
import com.gratattood.gratattood.model.Model_Cart;

import java.util.ArrayList;

public class Cart_Activity extends AppCompatActivity {

    TextView txtTotalPrice;
    Cart cart;
    double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);

        ListView yourListView = (ListView) findViewById(R.id.lst_Cart_Activity);
        cart = new Cart();

        MyCartListAdapter customAdapter = new MyCartListAdapter(this, R.layout.cart_row, getItemList());

        yourListView.setAdapter(customAdapter);
        yourListView.setFastScrollEnabled(true);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        txtTotalPrice.setText("$" + totalPrice + "");

    }

    public ArrayList<Model_Cart> getItemList() {

        ArrayList<Model_Cart> lst = new ArrayList<Model_Cart>();
        ArrayList<ModelTattooOrder> lst1 = new ArrayList<ModelTattooOrder>();
        lst1 = cart.getAllTattooOrders();

        for (int i = 0; i < lst1.size(); i++) {

            Model_Cart model_cart = new Model_Cart();

            model_cart.setComent(lst1.get(i).getTattoo_name());
            model_cart.setQuantity_number(lst1.get(i).getQuantity() + "");
            model_cart.setPrice("$" + lst1.get(i).getTattoo_price());
            totalPrice = totalPrice + lst1.get(i).getTattoo_price();
            model_cart.setTattoo(lst1.get(i).getBackground_image());

            lst.add(model_cart);
        }
        return lst;
    }

    public void onNextbtnClicked(View view) {
        if (totalPrice != 0) {

            Intent intent = new Intent(getBaseContext(), Checkout_Activity.class);
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "There is No Order ", Toast.LENGTH_LONG).show();
        }
    }

}


