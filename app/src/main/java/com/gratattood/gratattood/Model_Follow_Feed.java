package com.gratattood.gratattood;

/**
 * Created by Rafey Sheikh on 10/12/2017.
 */

public class Model_Follow_Feed {
/*int user_id;
    int image_id;*/
    String user_name;
    /*String dp_path;
    String tatto_path;*/
    String likes;
    String time;

   /* public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }*/

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

  /*  public String getDp_path() {
        return dp_path;
    }

    public void setDp_path(String dp_path) {
        this.dp_path = dp_path;
    }

    public String getTatto_path() {
        return tatto_path;
    }

    public void setTatto_path(String tatto_path) {
        this.tatto_path = tatto_path;
    }*/

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
