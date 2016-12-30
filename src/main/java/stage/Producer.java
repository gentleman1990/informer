package stage;

public class Producer {
    String towar;
    String typMagazynu;
    String typSamochodu;
    String ilosc;
    String cena;
    String cenaJednostkowa;
    String miasto;
    String odleglosc;

    public Producer(final String towar, final String typMagazynu, final String typSamochodu, final String ilosc, final String cena, final String cenaJednostkowa, final String miasto, final String odleglosc) {
        this.towar = towar;
        this.typMagazynu = typMagazynu;
        this.typSamochodu = typSamochodu;
        this.ilosc = ilosc;
        this.cena = cena;
        this.cenaJednostkowa = cenaJednostkowa;
        this.miasto = miasto;
        this.odleglosc = odleglosc;
    }

    public String getTowar() {
        return towar;
    }

    public String getTypMagazynu() {
        return typMagazynu;
    }

    public String getTypSamochodu() {
        return typSamochodu;
    }

    public String getIlosc() {
        return ilosc;
    }

    public String getCena() {
        return cena;
    }

    public String getCenaJednostkowa() {
        return cenaJednostkowa;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getOdleglosc() {
        return odleglosc;
    }
}
