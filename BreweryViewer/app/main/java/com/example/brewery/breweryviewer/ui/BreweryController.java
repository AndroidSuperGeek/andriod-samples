package com.example.brewery.breweryviewer.ui;

import android.os.Message;
import android.util.Log;

import com.example.brewery.breweryviewer.api.BreweryAPI;
import com.example.brewery.breweryviewer.model.Brewery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//api key
public class BreweryController {

    private static final String TAG = BreweryController.class.getSimpleName();

    private static final String BASE_URL = "http://api.brewerydb.com/v2/";

    private final String API_KEY= "7a84d421cd97993aaf2304b0b9788c00";

    private static BreweryController mInstance = new BreweryController();

    private BreweryAPI breweryAPI = null;
    private BreweryController(){
        Log.d(TAG,"init");
        start();
    }

    public static BreweryController getInstance(){
        return mInstance;
    }

    private void start() {

        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder().header("key", API_KEY);

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            breweryAPI = retrofit.create(BreweryAPI.class);
        }catch (Exception exc){
            Log.e(TAG,exc.toString());
        }

    }

    public void loadAllBreweries(final BreweriesLoader.BreweriesCallback callback){
        Call<List<Brewery>> call = breweryAPI.loadAll();
        call.enqueue(new Callback<List<Brewery>>() {
            @Override
            public void onResponse(Call<List<Brewery>> call, Response<List<Brewery>> response) {
                if(response.isSuccessful()) {
                    //Getting the result on from the API
                    List<Brewery> breweries = response.body();
                    callback.breweriesLoaded(response.message(),breweries);
                } else {
                    //notify the caller on error response.
                    Log.d(TAG,response.errorBody().toString());
                    callback.breweriesLoaded(response.message(),null);
                }
            }

            @Override
            public void onFailure(Call<List<Brewery>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
