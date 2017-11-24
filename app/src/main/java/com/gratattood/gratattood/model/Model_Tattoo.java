package com.gratattood.gratattood.model;

import java.io.Serializable;

/**
 * Created by abdur on 10/31/2017.
 */

public class Model_Tattoo implements Serializable {
    int id,user_id,waveform_id,type,public_or_private;
    double tattoo_price;
    String tattoo_name,background_image,created_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWaveform_id() {
        return waveform_id;
    }

    public void setWaveform_id(int waveform_id) {
        this.waveform_id = waveform_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPublic_or_private() {
        return public_or_private;
    }

    public void setPublic_or_private(int public_or_private) {
        this.public_or_private = public_or_private;
    }

    public double getTattoo_price() {
        return tattoo_price;
    }

    public void setTattoo_price(double tattoo_price) {
        this.tattoo_price = tattoo_price;
    }

    public String getTattoo_name() {
        return tattoo_name;
    }

    public void setTattoo_name(String tattoo_name) {
        this.tattoo_name = tattoo_name;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }
}
