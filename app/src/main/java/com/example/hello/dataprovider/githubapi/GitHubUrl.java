package com.example.hello.dataprovider.githubapi;

/**
 * Created by ysx on 2017/10/16.
 */

public final class GitHubUrl {
    private static final String TAG = "GitHubUrl";

    private GitHubUrl() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static final String HOST = "https://api.github.com";
    public static final String SEARCH_USER_URL = HOST + "/search/users";

}
