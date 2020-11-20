package woo.exceptions;

/**
 * Class for representing a duplicate/existing client key
 */
public class ExistingClientKeyException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 21392801130L;
    private final String _key;

    /**
    * @param key the duplicate key
    */
    public ExistingClientKeyException(String key) {
        _key = key;
    }

}
