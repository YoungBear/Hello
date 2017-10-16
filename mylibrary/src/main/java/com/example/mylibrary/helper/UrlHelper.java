package com.example.mylibrary.helper;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by ysx on 2017/10/16.
 */

public class UrlHelper implements Parcelable {


    protected HashMap<String, String> mStringValues = new HashMap<String, String>();

    /**
     * permtis to autorize empty parameter otherwise they are never added to
     * the url
     */
    protected boolean authorizeEmptyValue = true;

    private String mBaseUrl = "http://google.com/";

    public UrlHelper(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public static UrlHelper createUrl(String url) {
        UrlHelper nUrlHelper = new UrlHelper("");
        nUrlHelper.mBaseUrl = url;
        return nUrlHelper;
    }

    public UrlHelper clone() {
        UrlHelper urlHelper = new UrlHelper(mBaseUrl);
        urlHelper.mBaseUrl = mBaseUrl;
        urlHelper.mStringValues = (HashMap<String, String>) mStringValues.clone();
        urlHelper.authorizeEmptyValue = authorizeEmptyValue;
        return urlHelper;
    }

    public void setAutorizedEmptyValue(boolean autorizedEmptyValue) {
        this.authorizeEmptyValue = autorizedEmptyValue;
    }

    public Map<String, String> getValues() {
        return mStringValues;
    }

    public void setmStringValues(HashMap<String, String> mStringValues) {
        this.mStringValues = mStringValues;
    }

    public void appendValue(String id, int value) {
        mStringValues.put(id, Integer.toString(value));
    }

    public void appendValue(String id, long value) {
        mStringValues.put(id, Long.toString(value));
    }

    public void appendValue(String id, String value) {
        synchronized (this) {
            if (!authorizeEmptyValue) {
                if (TextUtils.isEmpty(value))
                    return;
            } else {
                if (value == null) {
                    return;
                }
            }
            mStringValues.put(id, value);
        }

    }

    public void removeKey(String id) {
        synchronized (this) {
            mStringValues.remove(id);
        }
    }

    /**
     * If the value is null or "" we remove this id from the map value
     * else we add the view
     *
     * @param id
     * @param value
     */
    public void appendOrRemoveValue(String id, String value) {
        synchronized (this) {
            if (TextUtils.isEmpty(value)) {
                removeKey(id);
            } else {
                appendValue(id, value);
            }
        }
    }

    public String getValue(String value) {
        return mStringValues.get(value);
    }

    public String getValueFull(String value) {
        StringBuilder sb = new StringBuilder(value);
        sb.append('=').append(mStringValues.get(value));
        return sb.toString();
    }

    public String getUrl() throws Exception {
        return createUrl(mBaseUrl, mStringValues, authorizeEmptyValue);
    }

    public String toString() {
        try {
            return getUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public URL getURL() throws MalformedURLException, Exception {
        return new URL(getUrl());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBaseUrl);
        dest.writeMap(getValues());
    }

    private UrlHelper(Parcel in) {
        ClassLoader mClassLoader = getClass().getClassLoader();
        mBaseUrl = in.readString();
        if (mStringValues == null)
            mStringValues = new HashMap<String, String>(5);
        in.readMap(mStringValues, mClassLoader);
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public static final Creator<UrlHelper> CREATOR = new Creator<UrlHelper>() {
        public UrlHelper createFromParcel(Parcel in) {
            return new UrlHelper(in);
        }

        public UrlHelper[] newArray(int size) {
            return new UrlHelper[size];
        }
    };

    public static String createUrl(String baseUrl, Map<String, String> values, boolean isEmptyParameterAuthorized) throws Exception {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?");
        addMapsToUrl(sb, values, false, isEmptyParameterAuthorized);
        return sb.toString();
    }

    protected static void addMapsToUrl(StringBuilder sb, Map<String, String> nMaps, boolean isComplete, boolean authorizedEmptyParameter) {
        HashMap<String, String> mMaps = new HashMap<String, String>();
        try {
            mMaps.putAll(nMaps);
        } catch (Exception e) {

        }
        int pos = 0;
        char c = ' ';
        String temp;
        if (mMaps != null) {
            Iterator<Entry<String, String>> mIt = mMaps.entrySet().iterator();
            ArrayList<String> keys = new ArrayList<String>();
            while (mIt.hasNext()) {
                Entry<String, String> mEntry = mIt.next();
                keys.add(mEntry.getKey());
            }

            Collections.sort(keys, new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    return rhs.toString().compareTo(lhs.toString());
                }
            });

            for (String key : keys) {
                if (pos == 0) {
                    c = sb.charAt(sb.length() - 1);
                    if (c != '?') {
                        if (c != '&')
                            sb.append('&');
                    }
                } else {
                    sb.append('&');
                }
                temp = mMaps.get(key);
                if (!authorizedEmptyParameter && TextUtils.isEmpty(temp)) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    if (isComplete) {
                        sb.append(temp);
                    } else {
                        sb.append(key);
                        sb.append('=');
                        if (!TextUtils.isEmpty(temp)) {
                            try {
                                sb.append(URLEncoder.encode(temp));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                pos++;
            }
        }
    }
}