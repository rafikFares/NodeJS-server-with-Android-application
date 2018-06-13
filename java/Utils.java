package com.example.paramedics.myrxjava.NodeJS;

public class Utils {

    public static final String url = "http://10.0.2.2:3000/nodeApi/";

    public static ApiInterface getApiInterface() {
        return RetrofitClient.getClient(url).create(ApiInterface.class);
    }
}
