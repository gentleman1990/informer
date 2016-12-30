import static utilities.CookieHandlerFromBrowser.getCookieUsingCookieHandler;
import static utilities.FieldFilters.filterFields;
import static utilities.ResponseParser.parseResponseForBuyers;
import static utilities.ResponseParser.parseResponseForProducers;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import requester.impl.RequsterImpl;
import stage.Buyer;
import stage.Producer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    @Parameter(names = {"-url", "--requestURL" }, description = "Request URL that will be used to send request on",required = true)
    private String requestUrl;

    @Parameter(names = {"-ot","--orderType"}, description = "Which type should be used to parse responses",required = true)
    private String orderType;

    public static void main(String[] args) throws InterruptedException {
        try {
            Main main = new Main();
            new JCommander(main, args);
            main.validate();

            main.runRequester();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runRequester() throws Exception {
        System.out.printf("Start sending requests ... ");
        List<String> responses = new ArrayList<String>();
        String phpsid = getCookieUsingCookieHandler();
        try {
            for (int i = 0; i < 36; i++) {
                RequsterImpl ri = new RequsterImpl();
                responses.add(ri.sendPost(requestUrl, filterFields(orderType,i),phpsid));

                Random rand = new Random();
                final int randomInt = rand.nextInt(15) + 15;
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
            switch(orderType){
                case "producer": printProducersOnList(parseResponseForProducers(s));break;
                case "buyer": printBuyersOnList(parseResponseForBuyers(s));break;
            }
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

    private void validate(){
        if((orderType.equals("buyer") && !requestUrl.contains("kupcow")) ||(orderType.equals("producer") && !requestUrl.contains("producentow"))){
            System.out.println("Please check that your request url is correct'");
            System.exit(1);
        }
    }
}
