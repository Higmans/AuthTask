package biz.lungo.authtask.helpers;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String generateQuery(HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (first) {
                sb.append(e.getKey()).append("=").append(e.getValue());
                first = false;
            } else {
                sb.append("&").append(e.getKey()).append("=").append(e.getValue());
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager syncManager=CookieSyncManager.createInstance(context);
            syncManager.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            syncManager.stopSync();
            syncManager.sync();
        }
    }
}
