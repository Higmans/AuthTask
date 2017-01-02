package biz.lungo.authtask.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("/issue/oauth2/authorize")
    Call<String> getNewAccessToken(
            @Field("client_id") String clientId,
            @Field("scope") String clientSecret,
            @Field("redirect_uri") String redirectUri);

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<String> getRefreshAccessToken(
            @Field("refresh_token") String refreshToken,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("redirect_uri") String redirectUri,
            @Field("grant_type") String grantType);
}
