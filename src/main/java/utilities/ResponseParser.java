package utilities;

import stage.Buyer;
import stage.Producer;
import java.util.ArrayList;
import java.util.List;

public class ResponseParser {

    public static List<Producer> parseResponseForProducers(final String response) {
        List<Producer> producersList = new ArrayList<Producer>();
        final String[] tds = response.split("<td");
        for (int i=2;i<tds.length-1;i=i+9) {
            producersList.add(new Producer(
                    tds[i].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+1].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+2].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+3].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+4].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+5].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+6].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+7].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"","")));
        }
        return producersList;
    }

    public static List<Buyer> parseResponseForBuyers(final String response) {
        List<Buyer> producersList = new ArrayList<Buyer>();
        final String[] tds = response.split("<td");
        for (int i=2;i<tds.length-1;i=i+10) {
            producersList.add(new Buyer(
                    tds[i].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+1].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+2].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\"","").replace("<br /",""),
                    tds[i+3].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+4].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+5].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+6].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"",""),
                    tds[i+7].replace(">","").replace("</td","").replace("\t",""),
                    tds[i+8].replace(">","").replace("</td","").replace("\t","").replace(" class=\"nowrap\" style=\"text-align:right;\"","")));
        }
        return producersList;
    }
}
