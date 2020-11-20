package woo;

public class Normal extends Client.ClientStatus {

    /**
     * @param client
     */
    public Normal(Client client) {
        client.super();
    }
    
    @Override
    public String status() {
        return "NORMAL";
    }
}
