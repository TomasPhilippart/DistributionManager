package woo;
import woo.exceptions.InvalidServiceTypeException;

/**
 * Class Box is a Product with a service type
 */
public class Box extends Product {

    private enum ServiceType { NORMAL, AIR, EXPRESS, PERSONAL }
    private ServiceType _serviceType;

    /** 
     * Initializes a Box given parameters.
     * @param key Box's key
     * @param price Box's price
     * @param criticalValue Box's critical value
     * @param stock Box's stock
     * @param supplierKey Box's supplier key
     * @param basePaymentTime time fraction that influences its final price
     * @param serviceType type of associated transport service
     * @throws InvalidServiceTypeException when an invalid service type is provided  
    */
    public Box(String key, int price, int criticalValue, int stock, String supplierKey, 
               int basePaymentTime, String serviceType)  throws InvalidServiceTypeException {
        super(key, price, criticalValue, supplierKey, stock, basePaymentTime);
        try {
            _serviceType = ServiceType.valueOf(serviceType);
        } catch (IllegalArgumentException e) {
            throw new InvalidServiceTypeException(serviceType);
        }
    }

    /** 
     * Initializes a Box given parameters.
     * Defaults basePaymentTime to 5.
     * @param key Box's key
     * @param price Box's price
     * @param criticalValue Box's critical value
     * @param stock Box's stock
     * @param supplierKey Box's supplier key
     * @param serviceType type of associated transport service
     * @throws InvalidServiceTypeException when an invalid service type is provided  
    */
    public Box(String key, int price, int criticalValue, int stock, String supplierKey, 
               String serviceType) throws InvalidServiceTypeException{
        this(key, price, criticalValue, stock, supplierKey, 5, serviceType); 
    }

    /* GETTER(S) */

    /** @return serviceType*/
    public String getServiceType() { return _serviceType.toString(); }
    
    @Override
    /** @return The Product Name ("BOX") */
    public String getProductName() { return "BOX"; }

    @Override
    public String toString() {
        return String.format("%s|%s", super.toString(), _serviceType.name());
    }
}

