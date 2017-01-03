package biz.lungo.authtask.helpers;

import biz.lungo.authtask.BuildConfig;

public class Constants {

    public static final String API_BASE_URL = "https://accounts.matrix42.com";
    public static final String API_LOGIN_URL = "https://accounts.matrix42.com/issue/oauth2/authorize";
    public static String CLIENT_ID;
    public static String SCOPE;
    public static String REDIRECT_URL;

    static {
        CLIENT_ID = BuildConfig.CLIENT_ID;
        SCOPE = BuildConfig.SCOPE;
        REDIRECT_URL = BuildConfig.REDIRECT_URL;
    }

    public class Preferences {
        public static final String FIELD_LOGGED_IN = "oauth.loggedIn";
        public static final String FIELD_ACCESS_TOKEN = "oauth.accessToken";
        public static final String FIELD_REFRESH_TOKEN = "oauth.refreshToken";
        public static final String FIELD_TOKEN_TYPE = "oauth.tokenType";
        public static final String FIELD_EXPIRATION_TIMESTAMP = "oauth.tokenExpirationTimestamp";
    }
}
