package woo;

public class Elite extends Client.ClientStatus {

    /**
     * @param client
     */
    public Elite(Client client) {
        client.super();
    }
    
    @Override
    public String status() {
        return "ELITE";
    }
}
