package biz.lungo.authtask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import biz.lungo.authtask.api.APIClient;
import biz.lungo.authtask.api.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String API_LOGIN_URL = "https://accounts.matrix42.com/issue/oauth2/authorize?client_id=51c465c2-3aa5-4dce-b248-c4e5a2df6e2b&scope=urn%3a58bdd3cb-f75a-44c2-a63e-b0831509caaa&redirect_uri=oauth%3a%2f%2fbiz.lungo.authtask&response_type=code";
    public static final String API_OAUTH_CLIENTID = "replace-me";
    public static final String API_OAUTH_CLIENTSECRET = "replace-me";
    public static final String API_OAUTH_REDIRECT = "oauth://biz.lungo.authtask";
    private AlertDialog loginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        WebView wv = new WebView(this);
        wv.loadUrl(API_LOGIN_URL);
        wv.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith(API_OAUTH_REDIRECT)) {
                    view.loadUrl(url);
                    return true;
                } else {
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                    return false;
                }
            }
        });

        builder.setView(wv);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        loginDialog = builder.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(API_OAUTH_REDIRECT)) {
            if (loginDialog != null && loginDialog.isShowing()) {
                loginDialog.dismiss();
            }
            String code = uri.getQueryParameter("code");
            if(code != null) {
                // TODO We can probably do something with this code! Show the user that we are logging them in

                final SharedPreferences prefs = this.getSharedPreferences(
                        BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

                APIClient client = ServiceGenerator.createService(APIClient.class);
                Call<String> call = client.getNewAccessToken(API_OAUTH_CLIENTID,
                        API_OAUTH_CLIENTSECRET, API_OAUTH_REDIRECT);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int statusCode = response.code();
                        if(statusCode == 200) {
                            String token = response.body();
                            System.out.println("SDLKJG: token = " + token);
//                            prefs.edit().putBoolean("oauth.loggedin", true).apply();
//                            prefs.edit().putString("oauth.accesstoken", token.getAccessToken()).apply();
//                            prefs.edit().putString("oauth.refreshtoken", token.getRefreshToken()).apply();
//                            prefs.edit().putString("oauth.tokentype", token.getTokenType()).apply();

                            // TODO Show the user they are logged in
                        } else {
                            // TODO Handle errors on a failed response
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        // TODO Handle failure
                    }
                });
            } else {
                // TODO Handle a missing code in the redirect URI
            }
        }
    }
}

