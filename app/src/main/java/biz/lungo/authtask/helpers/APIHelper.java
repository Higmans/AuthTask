package biz.lungo.authtask.helpers;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {

    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;

    public static <S> S createService(Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    public static String getLoginUrl() {
        HashMap<String, String> params = new HashMap<>();
        params.put("client_id", Constants.CLIENT_ID);
        params.put("scope", Constants.SCOPE);
        params.put("redirect_uri", Constants.REDIRECT_URL);
        params.put("response_type", "code");
        return Constants.API_LOGIN_URL + "?" + Utils.generateQuery(params);
    }
}
