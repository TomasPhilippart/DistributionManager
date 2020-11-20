package woo;

public class Selection extends Client.ClientStatus {

    /**
     * @param client
     */
    public Selection(Client client) {
        client.super();
    }

    @Override
    public String status() {
        return "SELECTION";
    }
}
