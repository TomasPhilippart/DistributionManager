package woo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Supplier contains information such as name, address,
 * activity status and is uniquely identified by a key
 */
public class Supplier implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 5607840932188329660L;
    
    private String _key;
    private String _name;
    private String _address;
    private boolean _isActive;
    private ArrayList<Order> _orders;

    // private Collection<Order> _orders;

     /** 
     * CONSTRUCTOR
     * Initializes a supplier given parameters
     * @param key suppliers's key.
     * @param name supplier's name.
     * @param address supplier's address.
     * @param isActive supplier's activity status (active/inactive).
     */
    public Supplier(String key, String name, String address, boolean isActive) {
        _key = key;
        _name = name;
        _address = address;
        _isActive = isActive;
        _orders = new ArrayList<Order>();
    }

     /** 
     * CONSTRUCTOR
     * Initializes a supplier given parameters
     * Defaults supplier as active
     * @param key suppliers's key.
     * @param name supplier's name.
     * @param address supplier's address.
     * @param isActive supplier's activity status (active/inactive).
     */
    public Supplier(String key, String name, String address) {
        this(key, name, address, true);
    }

    /* GETTERS */
    public String getKey() { return _key; }
    public String getName() { return _name; }
    public String getAddress() { return _address; }
    public boolean isActive() { return _isActive; }

    @Override
    public String toString() {
        //id|nome|endereço|activo?
        return String.format("%s|%s|%s", _key, _name, _address);
    }
}
