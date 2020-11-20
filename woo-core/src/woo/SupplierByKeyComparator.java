package woo;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Implements a comparator for clients based on their key
 */
public class SupplierByKeyComparator implements Comparator<Supplier>, Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -2924816255547141529L;

    @Override
    public int compare(Supplier s1, Supplier s2) {
        return s1.getKey().compareTo(s2.getKey());
    }

}