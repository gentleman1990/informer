package requester;

import org.apache.http.NameValuePair;
import java.util.List;

public interface Requester {
    final String USER_AGENT = "Mozilla/5.0";
    String sendPost(String url, List<NameValuePair> urlParameters, String phpSID);
}
