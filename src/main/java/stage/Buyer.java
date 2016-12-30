package stage;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;

public class Buyer {
    String towar;
    String typMagazynu;
    String termin;
    String ilosc;
    String zaplata;
    String cenaJednostkowa;
    String kara;
    String miasto;
    String odleglosc;

    public Buyer(final String towar, final String typMagazynu, final String termin, final String ilosc, final String zaplata, final String cenaJednostkowa, final String kara, final String miasto, final String odleglosc) {
        this.towar = towar;
        this.typMagazynu = typMagazynu;
        this.termin = termin;
        this.ilosc = ilosc;
        this.zaplata = zaplata;
        this.cenaJednostkowa = cenaJednostkowa;
        this.kara = kara;
        this.miasto = miasto;
        this.odleglosc = odleglosc;
    }

    public String getTowar() {
        return towar;
    }

    public String getTypMagazynu() {
        return typMagazynu;
    }

    public String getTermin() {
        return termin;
    }

    public String getIlosc() {
        return ilosc;
    }

    public String getZaplata() {
        return zaplata;
    }

    public String getCenaJednostkowa() {
        return cenaJednostkowa;
    }

    public String getKara() {
        return kara;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getOdleglosc() {
        return odleglosc;
    }


}
