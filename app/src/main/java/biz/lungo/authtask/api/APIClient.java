package biz.lungo.authtask.api;

import biz.lungo.authtask.BuildConfig;
import biz.lungo.authtask.models.ProfileInfo;
import biz.lungo.authtask.models.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("/issue/oauth2/token")
    @Headers("Authorization: " + BuildConfig.AUTH_HEADER)
    Call<TokenResponse> getNewToken(
            @Field("scope") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("redirect_uri") String redirectUri,
            @Field("code") String code);

    @GET("/api/session/profile")
    Call<ProfileInfo> getProfileInfo(
            @Header("Authorization") String authorizationHeader);
}
