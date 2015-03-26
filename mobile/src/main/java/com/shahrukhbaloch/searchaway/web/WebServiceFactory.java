package com.shahrukhbaloch.searchaway.web;

import com.google.android.gms.analytics.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class WebServiceFactory {

    public static String SERVER_URL = "https://api.instagram.com/v1/";
    private static WebService webService;
    private static Gson gson;

    public static WebService getInstanceWithBasicGsonConversion() {
        if (webService == null) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(createClient())
                    .setConverter(new GsonConverter(getGson()))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(SERVER_URL)
                    .build();

            webService = restAdapter.create(WebService.class);
        }
        return webService;
    }

    public static OkClient createClient() {
        OkHttpClient client = new OkHttpClient();

        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setConnectTimeout(15, TimeUnit.SECONDS);

        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS"); //$NON-NLS-1$
            sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }
        client.setSslSocketFactory(sslContext.getSocketFactory());
        return new OkClient(client);
    }

    private static Gson getGson() {

        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }


}
