package com.gratattood.gratattood.model;

import java.io.Serializable;

/**
 * Created by abdur on 11/20/2017.
 */

public class ModelTattooOrder extends Model_Tattoo implements Serializable {

   public ModelTattooOrder()
    {
        super();
    }

  public   int complex_to_print,available_to_sale,quantity;
    boolean size;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getSize() {
        return size;
    }

    public void setSize(boolean size) {
        this.size = size;
    }

    public int getComplex_to_print() {
        return complex_to_print;
    }

    public void setComplex_to_print(int complex_to_print) {
        this.complex_to_print = complex_to_print;
    }

    public int getAvailable_to_sale() {
        return available_to_sale;
    }

    public void setAvailable_to_sale(int available_to_sale) {
        this.available_to_sale = available_to_sale;
    }


}
