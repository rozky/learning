package com.rozarltd.module.betfairdata.download;

import com.google.common.base.Optional;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BetfairDataPage {
    private static final String frmDataBetfairCom = "107B980C73E5DD2B0C7BA8C1176F3AABC70A72441A36157AF5F20E9E24CFB88AC0EE9D8A484E8A7A6CB3C4DF365C3549423234E1C2AFFD5CA616F0B2D4764676BB49BE74DF4305D4038D2BBE8E4F3E2D5B3347FF1BE66CAEC1F0D44AB15DA278B33170E84494BA8A60A7A9B9C26B1DF9B23DAA21FB02920B910F2880B5C4DCAD9C9E6C9331997E801DFF9A51";
    private static final String ASP_NET_SESSION = "55i22uq1ap3rat45ns2z1055";
//    private static final String viewState = "dDw0ODMxODgxMDs7PrhIJKs8T7J7wL8yiOwiNZuFCIKn";
    private static final String viewState = "dDw0ODMxODgxMDs7PrhIJKs8T7J7wL8yiOwiNZuFCIKn";
//    private static final String fileId = "105ad369bc0ef53c36094b5699280858";
    private static final String fileId = "5d54fa1dc7cf012a5e6d1c9c936b70b0";
    private Document page;

    private static File PENDING_DIR = new File("c:\\development\\Betfair Data\\xxx\\");

    public static void main(String[] args) throws IOException {
//        Optional<BetfairDataPage> navigate = BetfairDataPage.navigate();
//        navigate.get().getOtherData();

        AcceptDownloadConditionsRequestBuilder requestBuilder = new AcceptDownloadConditionsRequestBuilder();
        HttpPost acceptDownloadConditionRequest = requestBuilder
                .viewState(viewState)
                .fileId(fileId)
                .build();

        DefaultHttpClient httpClient = new HttpClientBuilder().fiddlerProxy()
                .betfairSessionId(frmDataBetfairCom).aspNetSession(ASP_NET_SESSION).build();
        HttpResponse response = httpClient.execute(acceptDownloadConditionRequest);
        response.getEntity().getContent().close();





        if (isRedirectToDataFile(response)) {
            String dataFileLocation = response.getHeaders("Location")[0].getValue();

            System.out.println("Going to download file: " + dataFileLocation);

            HttpGet downloadRequest = new DownloadDataFileRequestBuilder().build(dataFileLocation);


            HttpResponse downloadResponse = httpClient.execute(downloadRequest);
            int statusCode = downloadResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("Downloading: " + dataFileLocation);
                File file = new File(PENDING_DIR + parseFileName(dataFileLocation));
                FileCopyUtils.copy(downloadResponse.getEntity().getContent(), new FileOutputStream(file));
                System.out.println("Download is finished: " + file.getAbsolutePath());
            }
        }
    }

    private static boolean isRedirectToDataFile(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] locations = response.getHeaders("Location");

        return statusCode == 302 && locations != null && locations[0].getValue().endsWith(".zip");
    }

    private boolean isRedirectToLoginForm(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] locations = response.getHeaders("Location");

        return statusCode == 302 && locations != null && locations[0].getValue().contains("login");
    }



    private BetfairDataPage(Document page) {
        this.page = page;
    }

    public static Optional<BetfairDataPage> navigate() {
        Document page;
        try {
            page = Jsoup.connect("http://data.betfair.com/").get();
        } catch (IOException e) {
            return Optional.absent();
        }

        return Optional.of(new BetfairDataPage(page));
    }

    public void getOtherData() {
        Elements downloadLinks = page.getElementById("tdOther").select("tr td a");
        System.out.println(downloadLinks.size());
        for (Element link : downloadLinks) {
            System.out.println(link.text());
        }
    }

    public static URI getUri() {
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>() {
                {
                    add(new BasicNameValuePair("file", "c34375db6b96059c257ccc92af430ed4"));
                }
            };

            return URIUtils.createURI(
                    "http",
                    "data.betfair.com",
                    -1,
                    "datastore/downloadfile.aspx",
                    URLEncodedUtils.format(params, "UTF-8"),
                    null);

        } catch (URISyntaxException e) {

            // todo
            return null;
        }
    }

    private String getResponseContent(HttpResponse response) throws IOException {
        byte[] content = FileCopyUtils.copyToByteArray(response.getEntity().getContent());
        return new String(content, 0, content.length);
    }

    private static String parseFileName(String fileLocation) {
        return fileLocation.replaceAll("/datastore/data/", "");
    }
}
