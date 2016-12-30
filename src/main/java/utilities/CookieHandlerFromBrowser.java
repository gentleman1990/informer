package utilities;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CookieHandlerFromBrowser {
    public static String getCookieUsingCookieHandler() {
        try {
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            URL url = new URL("http://s6.cargotycoon.pl/loginFromPortal.php?id=31788&id2=54398");
            URLConnection connection = url.openConnection();
            connection.getContent();

            java.net.CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            for (HttpCookie cookie: cookies) {
                if(cookie.getName().equals("PHPSESSID"))
                    return cookie.getValue();
                System.out.println("CookieHandler retrieved cookie: " + cookie);
            }
        } catch(Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
        return "";
    }
}
