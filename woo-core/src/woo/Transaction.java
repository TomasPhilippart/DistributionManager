package woo;

import java.io.Serializable;

public class Transaction implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 4836258399590381903L;
    private int _transactionID;

    /** 
     * CONSTRUCTOR
     * Initializes a transaction 
     * @param transactionID Transaction's unique identifier
    */
    public Transaction(int transactionID) {
        _transactionID = transactionID;
    }

    // TODO: implement pay method
}
