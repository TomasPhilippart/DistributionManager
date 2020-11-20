package woo;
import woo.exceptions.InvalidServiceLevelException;
import woo.exceptions.InvalidServiceTypeException;

/**
 * Class Container is a Box with a service level
 */
public class Container extends Box {

    private enum ServiceLevel { B4, C4, C5, DL }
    private ServiceLevel _serviceLevel;

    /** 
     * Initializes a Container given parameters.
     * @param key Container's key
     * @param price Container's price
     * @param criticalValue Container's critical value
     * @param supplierKey Container's supplier key
     * @param serviceType Container's service type
     * @param serviceLevel Container's service level
     * @param basePaymentTime time fraction that influences its final price
     * @throws InvalidServiceLevelException when an invalid service level is provided  
    */
    public Container(String key, int price, int criticalValue, int stock, String supplierKey, String serviceType, 
                     String serviceLevel, int basePaymentTime) throws InvalidServiceTypeException, InvalidServiceLevelException {
        super(key, price, criticalValue, stock, supplierKey, basePaymentTime, serviceType); 
        try {
            _serviceLevel = ServiceLevel.valueOf(serviceLevel);
        } catch (IllegalArgumentException e) {
            throw new InvalidServiceLevelException(serviceLevel);
        }
    }

    /** 
     * Initializes a Container given parameters.
     * Defaults basePaymentTime to 8.
     * @param key Container's key
     * @param price Container's price
     * @param criticalValue Container's critical value
     * @param supplierKey Container's supplier key
     * @param serviceType Container's service type
     * @param serviceLevel Container's service level
     * @throws InvalidServiceLevelException when an invalid service level is provided  
    */
    public Container(String key, int price, int criticalValue, int stock, String supplierKey, String serviceType, 
                     String serviceLevel) throws InvalidServiceTypeException, InvalidServiceLevelException {
        this(key, price, criticalValue, stock, supplierKey, serviceType, serviceLevel, 8);
    }

    /* GETTER(S) */

    /** @return serviceLevel */
    public String getServiceLevel() { return _serviceLevel.toString(); }
    
    @Override
    /** @return The Product Name ("CONTAINER") */
    public String getProductName() { return "CONTAINER"; }

    @Override
    public String toString() {
        return String.format("%s|%s", super.toString(), _serviceLevel.name());
    }
}

