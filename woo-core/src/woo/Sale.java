package woo;

public class Sale extends Transaction {
    private int _quantity;
    private int _limitDate;
    private boolean _isPaid;
    private Product _product;
    private Client _client;

    /** 
     * Initializes a sale given parameters.
     * @param Client Client who made the sale
     * @param Product Requested product
     * @param quantity Quantity of requested product
     * @param limitDate Payment deadline
    */
    public Sale(Client client, Product product, int quantity, int limitDate, int transactionID) {
        super(transactionID);
        _client = client;
        _product = product;
        _quantity = quantity;
        _limitDate = limitDate;
    }
}
