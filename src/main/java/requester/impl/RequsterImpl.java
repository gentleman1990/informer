package requester.impl;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import requester.Requester;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class RequsterImpl implements Requester {
    @Override
    public String sendPost(String url, List<NameValuePair> urlParameters, String phpSID) {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        prepareCookies(phpSID, client);

        String result = callRequestAndPrepareReturn(client, post);
        if (result != null) return result;
        return "";
    }

    private String callRequestAndPrepareReturn(final DefaultHttpClient client, final HttpPost post) {
        HttpResponse response = null;
        try {
            response = client.execute(post);

            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void prepareCookies(final String phpSID, final DefaultHttpClient client) {
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie = new BasicClientCookie("PHPSESSID", phpSID);
        cookie.setPath("/");
        cookie.setDomain("s6.cargotycoon.pl");
        cookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("portal", "1");
        cookie.setPath("/");
        cookie.setDomain("s6.cargotycoon.pl");
        cookieStore.addCookie(cookie);

        client.setCookieStore(cookieStore);
    }
}
