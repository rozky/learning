import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class ExampleTest {
    @Test
    public void shouldMatchRegExp() {
        // given
        String text = "/css/bundleA.css/css/bundleB.css";
        Pattern pattern = Pattern.compile("/css/bundleA\\.css");

        // when
        Matcher matcher = pattern.matcher(text);

        // then
        assertThat("find failed",matcher.find(), is(true));
        assertThat("matches failed",matcher.matches(), is(true));
    }

    @Test
    public void shouldPerformHttpGet() throws IOException {
        // given
        // when
        StopWatch watch = new StopWatch();
        watch.start();
        HttpGet httpGet = new HttpGet("http://uk.site.sports.betfair.com/betting/LoadCouponDataAction.do?ci=10041010");
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse execute = client.execute(httpGet);
        System.out.println("received response in : " + watch.getTime());

        byte[] content = FileCopyUtils.copyToByteArray(execute.getEntity().getContent());
        String contentAsString = new String(content, 0, content.length);
        System.out.println("done");

        Pattern pattern = Pattern.compile("p.m_R\\((.*)\\);");
        Matcher matcher = pattern.matcher(contentAsString);
        while(matcher.find()) {
            System.out.println(matcher.group(1));
        }
        System.out.println("received response in : " + watch.getTime());

        // then
    }
}
