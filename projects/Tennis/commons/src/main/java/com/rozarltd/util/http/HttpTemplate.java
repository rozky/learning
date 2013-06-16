package com.rozarltd.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public class HttpTemplate {

    public String getForContent(String url) throws HttpClientException {
        HttpGet httpGet = new HttpGet(url);
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpResponse execute = client.execute(httpGet);

            byte[] content = FileCopyUtils.copyToByteArray(execute.getEntity().getContent());
            return new String(content, 0, content.length);
        }
        catch (Exception exception) {
            throw new HttpClientException("Error occurred while executing GET request for the following URL: " + url, exception);
        }
    }

    public Document getForDocument(String url) throws HttpClientException {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new HttpClientException("Error occurred while executing GET request for the following URL: " + url, e);
        }
    }
}
