package com.rozarltd.module.betfairdata.download;

import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;

public class HttpClientBuilder {
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpHost proxy = null;

    public HttpClientBuilder proxy(String host, int port) {
        proxy = new HttpHost(host, port);
        return this;
    }

    public HttpClientBuilder fiddlerProxy() {
        proxy = new HttpHost("localhost", 8888);
        return this;
    }

    public HttpClientBuilder betfairSessionId(String betfairSessionId) {
        BasicClientCookie cookie = new BasicClientCookie("frmDataBetfairCom", betfairSessionId);
        cookie.setDomain("data.betfair.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        return this;
    }

    public HttpClientBuilder aspNetSession(String session) {
        BasicClientCookie cookie = new BasicClientCookie("ASP.NET_SessionId", session);
        cookieStore.addCookie(cookie);
        cookie.setDomain("data.betfair.com");
        cookie.setPath("/");
        return this;
    }

    public DefaultHttpClient build() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setCookieStore(cookieStore);

        if(proxy != null) {
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }

        return httpClient;
    }
}
