package woo;

import java.io.Serializable;

/**
 * Class Product contains information such as price, criticalLevel 
 * and stock and each product is uniquely identified by a string.
 */
public class Product implements Serializable {
    
    /** Serial number for serialization. */
    private static final long serialVersionUID = -5695019905652751306L;

    private String _key;
    private int _price;
    private int _criticalValue;
    private String _supplierKey;
    private int _stock;
    private int _basePaymentTime;

    /** 
     * Initializes a product given parameters
     * @param key key associated with the product
     * @param price price associated with the product
     * @param criticalValue critical level associated with the product
     * @param supplierKey supplier associated with the product
     * @param stock product's current stock
     * @param basePaymentTime product's time fraction that influences its final price
    */
    public Product(String key, int price, int criticalValue, String supplierKey, int stock, int basePaymentTime) {
        _key = key;
        _price = price;
        _criticalValue = criticalValue;
        _supplierKey = supplierKey;
        _stock = stock;
        _basePaymentTime = basePaymentTime;
    }

    /* GETTERS */

    /** @return Product's key*/
    public String getKey() { return _key; }

    /** @return Product's price*/
    public int getPrice() { return _price; }

    /** @return Product's critical level */
    public int getCriticalLevel() { return _criticalValue; }

    /** @return Product's supplier key */
    public String getSupplierKey() { return _supplierKey; }

    /** @return Product's stock */
    public int getStock() { return _stock; }

    /** @return Product's base payment time */
    public int getbasePaymentTime() { return _basePaymentTime; }

    /** @return Product's name ("PRODUCT")*/
    public String getProductName() { return "PRODUCT"; }

    /* SETTERS */

    /** @param Product's new price */
    public void setPrice(int newPrice) { _price = newPrice; }

    /** @return Product's new stock */
    public void setStock(int newStock) { _stock = newStock; }
    
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%d|%d|%d", getProductName(), _key, _supplierKey, _price, _criticalValue, _stock);
    }


}
