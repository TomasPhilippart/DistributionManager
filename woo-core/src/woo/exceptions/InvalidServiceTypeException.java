package woo.exceptions;

/**
 * Class for representing an invalid service type error.
 */
public class InvalidServiceTypeException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 20302308010L;
    private final String _serviceType;

    /**
     * @param serviceType
     */
    public InvalidServiceTypeException(String serviceType) {
        _serviceType = serviceType;
    }


}
