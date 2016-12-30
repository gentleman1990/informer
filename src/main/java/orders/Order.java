package orders;

import requester.impl.TycoonRequester;

public class Order {

    private String phpSessionId;

    public Order(final String phpSessionId) {
        this.phpSessionId = phpSessionId;
    }

    public String callRefreshOrder() throws Exception {

        TycoonRequester http = new TycoonRequester(phpSessionId);

        System.out.println("\nTesting - Send Http POST request");
        final String s = http.sendPost();
        return s;

    }
}
