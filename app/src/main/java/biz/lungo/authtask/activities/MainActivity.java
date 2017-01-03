package biz.lungo.authtask.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.net.URLDecoder;

import biz.lungo.authtask.BuildConfig;
import biz.lungo.authtask.R;
import biz.lungo.authtask.api.APIClient;
import biz.lungo.authtask.fragments.MainFragment;
import biz.lungo.authtask.helpers.APIHelper;
import biz.lungo.authtask.helpers.Constants;
import biz.lungo.authtask.models.ProfileInfo;
import biz.lungo.authtask.models.TokenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIClient client;
    private SharedPreferences preferences;
    private MainFragment mainFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = APIHelper.createService(APIClient.class);
        preferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        mainFragment = new MainFragment();
        getFragmentManager().beginTransaction().replace(R.id.rl_container, mainFragment, "main_fragment").commit();

        Uri uri = getIntent().getData();
        //noinspection deprecation
        if (uri != null && uri.toString().startsWith(URLDecoder.decode(Constants.REDIRECT_URL))) {
            showLoadingOverlay(true);
            finishAuthorization(uri);
            return;
        }
        boolean loggedIn = preferences.getBoolean(Constants.Preferences.FIELD_LOGGED_IN, false);
        long expirationTimestamp = preferences.getLong(Constants.Preferences.FIELD_EXPIRATION_TIMESTAMP, 0);
        if (!loggedIn || isTokenExpired(expirationTimestamp)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            getProfileInfo();
        }
    }

    private void getProfileInfo() {
        showLoadingOverlay(true);
        String accessToken = preferences.getString(Constants.Preferences.FIELD_ACCESS_TOKEN, "");
        Call<ProfileInfo> call = client.getProfileInfo("Bearer " + accessToken);
        call.enqueue(new Callback<ProfileInfo>() {
            @Override
            public void onResponse(Call<ProfileInfo> call, Response<ProfileInfo> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    ProfileInfo profileInfo = response.body();
                    pushInfoToFragment(profileInfo);
                } else {
                    Toast.makeText(MainActivity.this, "Request failed. Code = " + statusCode, Toast.LENGTH_LONG).show();
                    pushInfoToFragment(null);
                }
                showLoadingOverlay(false);
            }

            @Override
            public void onFailure(Call<ProfileInfo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request failed. Error = " + t.getMessage(), Toast.LENGTH_LONG).show();
                pushInfoToFragment(null);
                showLoadingOverlay(false);
            }
        });
    }

    private void pushInfoToFragment(ProfileInfo profileInfo) {
        if (mainFragment != null && mainFragment.isAdded()) {
            mainFragment.setProfileInfo(profileInfo);
        }
    }

    private void finishAuthorization(Uri uri) {
        String code = uri.getQueryParameter("code");
        if (code != null) {
            Call<TokenResponse> call = client.getNewToken(Constants.SCOPE, "authorization_code", Constants.REDIRECT_URL, code);
            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    int statusCode = response.code();
                    if(statusCode == 200) {
                        TokenResponse tokenResponse = response.body();
                        saveData(tokenResponse);
                        getProfileInfo();
                    } else {
                        Toast.makeText(MainActivity.this, "Fail. Code = " + statusCode, Toast.LENGTH_LONG).show();
                    }
                    showLoadingOverlay(false);
                }
                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Fail. Error = " + t.getMessage(), Toast.LENGTH_LONG).show();
                    showLoadingOverlay(false);
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Fail. Empty code", Toast.LENGTH_LONG).show();
            showLoadingOverlay(false);
        }
    }

    private void saveData(TokenResponse tokenResponse) {
        SharedPreferences prefs = this.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        long expirationTimestamp = System.currentTimeMillis() + (tokenResponse.getExpiresIn() * 1000);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Constants.Preferences.FIELD_LOGGED_IN, true);
        editor.putString(Constants.Preferences.FIELD_ACCESS_TOKEN, tokenResponse.getAccessToken());
        editor.putString(Constants.Preferences.FIELD_REFRESH_TOKEN, tokenResponse.getRefreshToken());
        editor.putString(Constants.Preferences.FIELD_TOKEN_TYPE, tokenResponse.getTokenType());
        editor.putLong(Constants.Preferences.FIELD_EXPIRATION_TIMESTAMP, expirationTimestamp);
        editor.apply();
    }

    private boolean isTokenExpired(long expirationTimestamp) {
        return System.currentTimeMillis() > expirationTimestamp;
    }

    private void showLoadingOverlay(boolean show) {
        View rlLoading = findViewById(R.id.rl_loading);
        rlLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void signOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.Preferences.FIELD_LOGGED_IN, false);
        editor.remove(Constants.Preferences.FIELD_ACCESS_TOKEN);
        editor.remove(Constants.Preferences.FIELD_REFRESH_TOKEN);
        editor.remove(Constants.Preferences.FIELD_TOKEN_TYPE);
        editor.remove(Constants.Preferences.FIELD_EXPIRATION_TIMESTAMP);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
