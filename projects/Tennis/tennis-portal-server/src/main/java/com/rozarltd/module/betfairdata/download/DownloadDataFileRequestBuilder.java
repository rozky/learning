package com.rozarltd.module.betfairdata.download;

import org.apache.http.client.methods.HttpGet;

public class DownloadDataFileRequestBuilder {
    private static final String DOWNLOAD_URL = "http://data.betfair.com/datastore/downloadfile.aspx?file=%s";

    // /datastore/data/bfinf_other_120312to120318_120321122829.zip
    public HttpGet build(String location) {
        HttpGet request = new HttpGet("http://data.betfair.com" + location);
        return request;
    }
}
