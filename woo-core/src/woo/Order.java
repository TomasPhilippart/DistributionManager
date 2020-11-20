package woo;

public class Order extends Transaction {
    
    private int _price;
    private Supplier _supplier;
    private int _quantity;
    private int _limitDate;

    /** 
     * Initializes an Order given parameters.
     * @param Client Client who made the sale
     * @param Product Requested product
     * @param quantity Quantity of requested product
     * @param limitDate Payment deadline
    */
    public Order(Supplier supplier, int transactionID, int quantity, int limitDate) {
        super(transactionID);
        _supplier = supplier;
        _quantity = quantity;
        _limitDate = limitDate;
    }
}
