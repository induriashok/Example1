package com.joystays.joy.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

       /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Map<String, String> paramsHeader = new HashMap<>();
                paramsHeader.put("Content-Type", "application/json");
                //  paramsHeader.put("Authorization", "Basic RW1tc2g6RW1tc2hAMjAxOA==");
                paramsHeader.put("Authorization", "Basic dGFoeWJhOnRhaHlhYmFfMjAxOQ==");
                Headers headers = Headers.of(paramsHeader);
                Request request = chain.request().newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;
    }

}
