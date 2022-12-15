package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.beans.CatBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.example.Main.URL_RANDOM_CAT;

public class RequestUtils {

    private CatBeans cat ;

    public static CatBeans loadCatBean() throws Exception {

        String responseJson = sendGet(URL_RANDOM_CAT);
        //Parser le JSON avec le bon bean et GSON
        ArrayList<CatBeans> listCat = new Gson().fromJson(responseJson, new TypeToken<ArrayList<CatBeans>>(){}.getType());
        int ind = new Random().nextInt(listCat.size());
        CatBeans randomCat = listCat.get(ind);
        // if the json file is directly a json object not a list
//        CatBeans randomCat = new Gson().fromJson(responseJson, CatBeans.class);

        return   randomCat;

    }
    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
        finally {
            if(response != null) {
                response.close();
            }
        }
    }
}

//https://api.thecatapi.com/v1/images/search?limit=10