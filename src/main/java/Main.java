
import static utilities.Common.activatePopUp;
import static utilities.ResponseParser.parseResponseForBuyers;
import static utilities.ResponseParser.parseResponseForProducers;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import requester.Order;
import requester.ProducresRequester;
import stage.Buyer;
import stage.Producer;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    @Parameter(names = {"-sid", "--phpSessionId" }, description = "PHP Session Id from cookies",required = true)
    private String phpSessionId;

    @Parameter(names = {"-ot","--orderType"}, description = "What should be founded in response",required = true)
    private String orderType;

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws InterruptedException {
        try {
            Main main = new Main();
            new JCommander(main, args);
            //main.runTycoonRequester();
            main.runProducersRequester();
            //main.getCookieUsingCookieHandler();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runTycoonRequester() throws Exception {
        System.out.printf("%s %s \n", phpSessionId, orderType);

        while (true) {
            //for (int i = 0; i < 5; i++) {
                Order c = new Order(phpSessionId);
                final String response = c.callRefreshOrder();
                if (response.contains(orderType)) {
                    System.out.println("Znleziono zlecenie dla początkujących!");
                    activatePopUp();

                }

                Random rand = new Random();
                final int randomInt = rand.nextInt(90) + 120;
                System.out.println("Waiting time: " + randomInt + "second ...");
                Thread.sleep(randomInt * 1000);
            //}
        }
    }

    public void runProducersRequester() throws Exception {
        System.out.printf("%s %s \n", phpSessionId, orderType);
        List<String> responses = new ArrayList<String>();
        String phpsid = getCookieUsingCookieHandler();
        try {
            for (int i = 0; i < 6; i++) {
                Order c = new Order(phpSessionId);
                //ProducresRequester pr = new ProducresRequester(phpSessionId);
                final String response = new ProducresRequester().sendPost(i,phpsid);
                responses.add(response);

                Random rand = new Random();
                final int randomInt = rand.nextInt(15) + 15;
                //final int randomInt = rand.nextInt(5)+10;
                System.out.println("Waiting time: " + randomInt + "second ...");
                Thread.sleep(randomInt * 1000);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            printResponses(responses);
        }
        printResponses(responses);
    }

    private void printResponses(final List<String> responses) {
        for (String s : responses) {
            //printProducersOnList(parseResponseForProducers(s));
            printBuyersOnList(parseResponseForBuyers(s));
        }
    }

    private void printProducersOnList(final List<Producer> producersList) {
        for(Producer p : producersList){
            System.out.println(p.getTowar() + ";" + p.getTypMagazynu() + ";" + p.getTypSamochodu() + ";" + p.getIlosc() + ";" + p.getCena() + ";" + p.getCenaJednostkowa() + ";" + p.getMiasto() + ";" + p.getOdleglosc());
        }
    }

    private void printBuyersOnList(final List<Buyer> producersList) {
        for(Buyer b : producersList){
            System.out.println(b.getTowar() + ";" + b.getTypMagazynu() + ";" + b.getTermin() + ";" + b.getIlosc() + ";" + b.getZaplata() + ";" + b.getCenaJednostkowa() + ";" + b.getKara() + ";" + b.getMiasto() + ";" + b.getOdleglosc() );
        }
    }

    public String getCookieUsingCookieHandler() {
        try {
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            URL url = new URL("http://s6.cargotycoon.pl/loginFromPortal.php?id=31788&id2=54398");
            URLConnection connection = url.openConnection();
            connection.getContent();

            java.net.CookieStore cookieJar =  manager.getCookieStore();
            List <HttpCookie> cookies =
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
