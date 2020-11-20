package woo;

/**
 * Class Book contains information about a book, 
 * which has an author, a title and a ISBN */
public class Book extends Product {

    private String _author;
    private String _title;
    private String _ISBN;

    /** 
     * Initializes a book given parameters.
     * @param key Book's key
     * @param price Book's price
     * @param criticalValue Book's critical value
     * @param supplierKey Book's supplier key
     * @param stock Book's current stock
     * @param author Book's author
     * @param title Book's title
     * @param ISBN  Book's ISBN
     * @param basePaymentTime time fraction that influences its final price
    */
    public Book(String key, int price, String author, String title, String ISBN, int stock, int criticalValue, String supplierKey, int basePaymentTime) {
        super(key, price, criticalValue, supplierKey, stock, basePaymentTime);
        _author = author;
        _title = title;
        _ISBN = ISBN;
    }

    /** 
     * Initializes a book given parameters.
     * Defaults basePaymentTime to 3.
     * @param key Book's key
     * @param price Book's price
     * @param criticalValue Book's critical value
     * @param supplierKey Book's supplier key
     * @param stock Book's current stock
     * @param author Book's author
     * @param title Book's title
     * @param ISBN  Book's ISBN
    */
    public Book(String key, int price, String author, String title, String ISBN, int stock, int criticalValue, String supplierKey) {
        this(key, price, author, title, ISBN, stock, criticalValue, supplierKey, 3);
    }

    /* GETTER(S) */

    /** @return Book's author */
    public String getAuthor() { return _author; }

    /** @return Book's Title */
    public String getTitle() { return _title; }

    /** @return Book's ISBN */
    public String getISBN() { return _ISBN; }

    @Override
    /** @return The product name ("BOOK") */
    public String getProductName() { return "BOOK"; }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", super.toString(), _title, _author, _ISBN);
    }
}

