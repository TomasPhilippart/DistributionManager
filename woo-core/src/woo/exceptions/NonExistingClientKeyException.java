package woo.exceptions;

/**
 * Class for representing a non-existing client key
 */
public class NonExistingClientKeyException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 21992701131L;
    private final String _key;

    /**
    * @param key the non-existing key
    */
    public NonExistingClientKeyException(String key) {
        _key = key;
    }

}
