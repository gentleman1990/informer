package utilities;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;

public class FieldFilters {
    public static List<NameValuePair> filterFields(String orderType, int pageNumber){
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        switch(orderType){
            case "producer":
                return prepareUrlParametersForProducers("Wszystkie","","","","","ilosc_malejaco","","",Integer.toString(pageNumber));
            case "buyer":
                return prepareUrlParametersForBuyers("Wszystkie","","","","","ilosc_malejaco","",Integer.toString(pageNumber));
            default: throw new IllegalArgumentException("Unsupported orderType, you can choose from: buyer or producer");
        }
    }
    private static List<NameValuePair> prepareUrlParametersForProducers(String towar, String zyskMin, String zyskMax, String miasto, String garazMiasto, String rodzajMagazyn, String sortowanie, String typSamochodu, String page){
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("towar", towar));
        urlParameters.add(new BasicNameValuePair("zysk_min", zyskMin));
        urlParameters.add(new BasicNameValuePair("zysk_max", zyskMax));
        urlParameters.add(new BasicNameValuePair("miasto", miasto));
        urlParameters.add(new BasicNameValuePair("garaz_miasto", garazMiasto));
        urlParameters.add(new BasicNameValuePair("rodzaj_magazyn",rodzajMagazyn));
        urlParameters.add(new BasicNameValuePair("sortowanie", sortowanie));
        urlParameters.add(new BasicNameValuePair("typ_samochodu", typSamochodu));
        urlParameters.add(new BasicNameValuePair("page", page));
        return urlParameters;
    }
    private static List<NameValuePair> prepareUrlParametersForBuyers(String towar, String zyskMin, String zyskMax, String miasto, String garazMiasto, String sortowanie, String typSamochodu, String page){
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("towar", towar));
        urlParameters.add(new BasicNameValuePair("zysk_min", zyskMin));
        urlParameters.add(new BasicNameValuePair("zysk_max", zyskMax));
        urlParameters.add(new BasicNameValuePair("miasto", miasto));
        urlParameters.add(new BasicNameValuePair("garaz_miasto", garazMiasto));
        urlParameters.add(new BasicNameValuePair("sortowanie", sortowanie));
        urlParameters.add(new BasicNameValuePair("typ_samochodu", typSamochodu));
        urlParameters.add(new BasicNameValuePair("page", page));
        return urlParameters;
    }
}
