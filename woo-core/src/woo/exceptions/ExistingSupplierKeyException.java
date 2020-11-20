package woo.exceptions;

/**
 * Class for representing a duplicate/existing supplier key
 */
public class ExistingSupplierKeyException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 21492831932L;
    private final String _key;

    /**
    * @param key the duplicate key
    */
    public ExistingSupplierKeyException (String key) {
        _key = key;
    }

}
