package com.rozarltd.module.betfairdata.download;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class AcceptDownloadConditionsRequestBuilder {
    private static final String DOWNLOAD_URL = "http://data.betfair.com/datastore/downloadfile.aspx?file=%s";
    private String fileId;
    private String viewState;

    public AcceptDownloadConditionsRequestBuilder fileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public AcceptDownloadConditionsRequestBuilder viewState(String viewState) {
        this.viewState = viewState;
        return this;
    }

    public HttpPost build() {
        HttpPost request = new HttpPost(String.format(DOWNLOAD_URL, fileId));
        request.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        request.setHeader(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
        request.setHeader(new BasicHeader("Origin", "http://data.betfair.com"));
        request.setHeader(new BasicHeader("Referer", String.format(DOWNLOAD_URL, fileId)));
        request.setHeader(new BasicHeader("Host", "data.betfair.com"));

        NameValuePair[] nameValuePairs = new NameValuePair[] {
            new BasicNameValuePair("__VIEWSTATE", viewState),
            new BasicNameValuePair("btnAccept", "Download"),
                new BasicNameValuePair("file", fileId)
        };

        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(Arrays.asList(nameValuePairs));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Error while URL encoding request content", e);
        }
        request.setEntity(entity);

        return request;
    }
}
