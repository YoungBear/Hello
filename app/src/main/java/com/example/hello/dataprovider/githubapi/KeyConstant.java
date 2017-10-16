package com.example.hello.dataprovider.githubapi;

/**
 * Created by ysx on 2017/10/16.
 */

public final class KeyConstant {
    private static final String TAG = "KeyConstant";
    private KeyConstant() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    // OAuth
    // client_id
    public static final String CLIENT_ID = "client_id";
    // client_secret
    public static final String CLIENT_SECRET = "client_secret";

    //use OAuth application to exceed rate limit from 60 to 5000 per hour
    // client_id value
    public static final String CLIENT_ID_VALUE = "8fc37e449c0a481ee319";
    // client_secret value
    public static final String CLIENT_SECRET_VALUE = "80d1703b47b4581a42570e20248d924a9eb57797";

    // search key
    // 搜索的关键词
    public static final String KEY_WORD = "q";
    // 每页的大小
    public static final String PER_PAGE = "per_page";
    // 当前页
    public static final String PAGE = "page";


}
