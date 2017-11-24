package com.gratattood.gratattood.app;

/**
 * Created by Rafey Sheikh on 10/2/2017.
 */

public class AppConfig {
    // Server Base URL
    public static String BASE_URL = "http://demo.byterace.com/gratattood/api/";

    // Server user login url
    public static String URL_LOGIN = BASE_URL + "login_user.php";

    // Server user register url
    public static String URL_REGISTER = BASE_URL + "register_user.php";

    // Server get all tattoos url
    public static String URL_GET_ALL_TATTOOS = BASE_URL + "get_all_tattoos.php";
    // Server get  tattoos details url
    public static String URL_GET_TATTOO_DETAIL = BASE_URL + "get_tattoo_detail.php";
}
