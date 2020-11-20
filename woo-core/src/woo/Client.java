package woo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Client contains information such as name, address,
 * points and uniquely identifies a client by a string.
 */
public class Client implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 76526628159136372L;
    
    private String _key;
    private String _name;
    private String _address;
    private int _points;
    private ClientStatus _clientStatus;
    private ArrayList<Sale> _sales;
    private int _paidSalesCount;

    /**
     * Manages a client's status and calculates 
     * the price for a given sale according to it.
    */
    public abstract class ClientStatus implements Serializable{

        /** @return the client's status */
        public abstract String status();
         
        /** @return the current client */
        protected Client getClient() {
            return Client.this;
        }
    }

    /** 
     * Initializes a client given parameters.
     * @param key Client's key
     * @param name Client's name
     * @param address Client's address
     * @param points Client's points
    */
    public Client(String key, String name, String address, int points) {
        _key = key;
        _name = name;
        _address = address;
        _points = points;
        _clientStatus = new Normal(this);
        _sales = new ArrayList<Sale>();
        _paidSalesCount = 0;
    } 

    /** 
     * Initializes a client given parameters.
     * Defaults points to 0.
     * @param key Client's key
     * @param name Client's name
     * @param address Client's address
    */
    public Client(String key, String name, String address) {
        this(key, name, address, 0);
    } 

    /* GETTERS */
    public String getKey() { return _key; }
    public String getName() { return _name; }
    public String getAddress() { return _address; }
    public int getPoints() { return _points; }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%d|%d", _key, _name, _address, _clientStatus.status(), _paidSalesCount, _sales.size());
    }
}
