package woo;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Implements a comparator for clients based on their key
 */
public class ProductByKeyComparator implements Comparator<Product>, Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -5572142453830606025L;

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getKey().compareTo(p2.getKey());
    }

}
