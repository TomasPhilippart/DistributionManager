package woo.exceptions;

/**
 * Class for representing an invalid service level error.
 */
public class InvalidServiceLevelException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 21302402130L;
    private final String _serviceLevel;

    /**
     * @param serviceLevel
     */
    public InvalidServiceLevelException(String serviceLevel) {
        _serviceLevel = serviceLevel;
    }


}
