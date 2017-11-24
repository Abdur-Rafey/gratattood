package com.gratattood.gratattood.model;

import java.util.ArrayList;

/**
 * Created by abdur on 11/20/2017.
 */

public class Cart {
  private static  ArrayList<ModelTattooOrder> ordersList=new ArrayList<>();

    public ArrayList<ModelTattooOrder> getAllTattooOrders() {
        return ordersList;
    }

    public void addTattooOrderToCart(ModelTattooOrder o) {
        this.ordersList.add(o);
    }
}
