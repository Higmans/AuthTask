package biz.lungo.authtask.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.net.URLDecoder;

import biz.lungo.authtask.R;
import biz.lungo.authtask.helpers.APIHelper;
import biz.lungo.authtask.helpers.Constants;
import biz.lungo.authtask.helpers.Utils;

public class LoginActivity extends AppCompatActivity {

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            ((RelativeLayout)view.getParent()).findViewById(R.id.rl_loading).setVisibility(View.GONE);
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.startsWith(URLDecoder.decode(Constants.REDIRECT_URL)) &&
                    !url.contains("forgotpassword") &&
                    !url.contains("register")) {
                view.loadUrl(url);
                return true;
            } else {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
                return true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tryLogin();
            }
        });
    }

    private void tryLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View rlDialog = View.inflate(this, R.layout.dialog_login, null);
        WebView wv = (WebView) rlDialog.findViewById(R.id.wv_dialog);
        rlDialog.findViewById(R.id.rl_loading).setVisibility(View.VISIBLE);
        wv.clearCache(true);
        Utils.clearCookies(this);
        wv.loadUrl(APIHelper.getLoginUrl());
        wv.setWebViewClient(webViewClient);
        builder.setView(rlDialog);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog loginDialog = builder.show();
        Window window = loginDialog.getWindow();
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
    }
}

