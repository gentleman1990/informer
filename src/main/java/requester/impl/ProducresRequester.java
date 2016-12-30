package requester.impl;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProducresRequester {
    private final String USER_AGENT = "Mozilla/5.0";
    //private String phpSessionId = "";

    //public ProducresRequester(final String phpSessionId) {
//        this.phpSessionId = phpSessionId;
//    }

    // HTTP POST request
    public String sendPost(int pageNumber, String phpsid) throws Exception {

        //String url = "http://s6.cargotycoon.pl/ajax/2/wybierz_producentow.php";
        String url = "http://s6.cargotycoon.pl/ajax/2/wybierz_kupcow.php";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-type", "application/x-www-form-urlencoded");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("towar", "Wszystkie"));
        urlParameters.add(new BasicNameValuePair("zysk_min", ""));
        urlParameters.add(new BasicNameValuePair("zysk_max", ""));
        urlParameters.add(new BasicNameValuePair("miasto", ""));
        urlParameters.add(new BasicNameValuePair("garaz_miasto", ""));
        //urlParameters.add(new BasicNameValuePair("rodzaj_magazyn","Wszystkie"));
        urlParameters.add(new BasicNameValuePair("sortowanie", "ilosc_malejaco"));
        urlParameters.add(new BasicNameValuePair("typ_samochodu", "3"));
        urlParameters.add(new BasicNameValuePair("page", Integer.toString(pageNumber)));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie = new BasicClientCookie("PHPSESSID", phpsid);
        cookie.setPath("/");
        cookie.setDomain("s6.cargotycoon.pl");
        cookieStore.addCookie(cookie);

        cookie = new BasicClientCookie("portal", "1");
        cookie.setPath("/");
        cookie.setDomain("s6.cargotycoon.pl");
        cookieStore.addCookie(cookie);

        client.setCookieStore(cookieStore);


        HttpResponse response = client.execute(post);
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
    }

}
