package woo;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Implements a comparator for clients based on their key
 */
public class ClientByKeyComparator implements Comparator<Client>, Serializable {

    
    /** Serial number for serialization. */
    private static final long serialVersionUID = -7513602090175764134L;

    @Override
    public int compare(Client c1, Client c2) {
        return c1.getKey().compareTo(c2.getKey());
    }

}
