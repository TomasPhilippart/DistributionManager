package woo.exceptions;

/**
 * Class for representing an invalid service level error.
 */
public class InvalidNumberOfDaysException extends Exception {

	/** Serial number for serialization. */
    private static final long serialVersionUID = 21302402130L;
    private final int _numberOfDays;

    /**
     * @param serviceLevel
     */
    public InvalidNumberOfDaysException(int numberOfDays) {
        _numberOfDays = numberOfDays;
    }


}
