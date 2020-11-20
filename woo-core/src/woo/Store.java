package woo;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import woo.exceptions.*;
import java.util.regex.*;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202009192006L;

    private int _date;
    private int _numberOfTransactions;
    private float _availableBalance;

    private HashMap<String, Client> _clients = new HashMap<String, Client>();
    private HashMap<String, Product> _products = new HashMap<String, Product>();
    private HashMap<String, Supplier> _suppliers = new HashMap<String, Supplier>();

    /** 
     * Initializes a store.
     * @param date store's starting date.
    */
    public Store(int date) {
        _date = date;
    }

    /** 
     * Initializes a store.
     * Defaults starting date to 0.
    */
    public Store() {
        this(0);
    }

    /** 
     * Getter for current date
     * @return Current date
     */ 
    public int getCurrentDate() { return _date; }

    /**
     * Advances date according to user input
     * @param numberOfDays
     * @throws InvalidNumberOfDaysException
     */
    public void advanceDate(int numberOfDays) throws InvalidNumberOfDaysException {
        if (numberOfDays >= 0) {
            _date += numberOfDays;
        } else {
            throw new InvalidNumberOfDaysException(numberOfDays);
        }
    }

    /**
    * @param txtfile filename to be loaded.
    * @throws IOException
    * @throws BadEntryException
    * @throws ExistingClientKeyException
    * @throws ExistingSupplierKeyException
    * @throws ExisingProductKeyException
    * @throws InvalidServiceLevelException
    * @throws InvalidServiceTypeException
    * @throws NonExistingSupplierKeyException
    */
    void importFile(String txtfile) throws IOException, BadEntryException, ExistingClientKeyException, 
                                           ExistingSupplierKeyException, ExistingProductKeyException, 
                                           InvalidServiceLevelException, InvalidServiceTypeException,
                                           NonExistingSupplierKeyException {

        BufferedReader reader = new BufferedReader(new FileReader(txtfile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                registerFromFields(fields);
            }
        reader.close();
    }

    /**
     * Registers a Client, Supplier or a Product based on a string array of fields.
    * @param fields 
    * @throws BadEntryException
    * @throws ExistingClientKeyException
    * @throws ExistingSupplierKeyException
    * @throws ExisingProductKeyException
    * @throws InvalidServiceLevelException
    * @throws InvalidServiceTypeException
    * @throws NonExistingSupplierKeyException
    */
    void registerFromFields(String[] fields) throws BadEntryException, ExistingClientKeyException, 
                                                    ExistingSupplierKeyException, ExistingProductKeyException, 
                                                    InvalidServiceLevelException, InvalidServiceTypeException,
                                                    NonExistingSupplierKeyException {
        // Regular expression pattern to match a client.
        Pattern patClient = Pattern.compile("^(CLIENT)");
        // Regular expression pattern to match a supplier..
        Pattern patSupplier = Pattern.compile("^(SUPPLIER)");
        // Regular expression pattern to match a product.
        Pattern patProduct = Pattern.compile("^(BOX|CONTAINER|BOOK)");

        if (patClient.matcher(fields[0]).matches()) {
            registerClientFromFields(fields);
        } else if (patSupplier.matcher(fields[0]).matches()) {
            registerSupplierFromFields(fields);
        } else if (patProduct.matcher(fields[0]).matches()) {
            registerProductFromFields(fields);
        } else {
            throw new BadEntryException(fields[0]);
        }
    }

    /**
     * Registers a client from a string array of fields
    * @param fields 
    * @throws ExistingClientKeyException
    */
    public void registerClientFromFields(String[] fields) throws ExistingClientKeyException { 
        registerClient(fields[1], fields[2], fields[3]); 
    }

    /**
     * Registers a supplier from a string array of fields
    * @param fields 
    * @throws ExistingSupplierKeyException
    */
    public void registerSupplierFromFields(String[] fields) throws ExistingSupplierKeyException { 
        registerSupplier(fields[1], fields[2], fields[3]); 
    }

    /**
     * Registers a product from a string array of fields
    * @param fields 
    * @throws BadEntryException
    * @throws ExisingProductKeyException
    * @throws InvalidServiceLevelException
    * @throws InvalidServiceTypeException
    * @throws NonExistingSupplierKeyException
    */
    public void registerProductFromFields(String[] fields) throws ExistingProductKeyException, 
                                                                  NonExistingSupplierKeyException, InvalidServiceTypeException, 
                                                                  InvalidServiceLevelException { 
        if (fields[0].equals("BOX")) {
            /* String key, int price, int criticalValue, int stock, String supplierKey, String serviceType*/
            registerProductBox(fields[1], Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), fields[3], fields[2]);

        } else if (fields[0].equals("CONTAINER")) {
            /* String key, int price, int criticalValue, int stock, String supplierKey, String serviceType, String serviceLevel*/
            registerProductContainer(fields[1], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), fields[4], fields[2], fields[3]);

        } else if (fields[0].equals("BOOK")) {
            /* String key, int price, String author, String title, String ISBN, int stock, int criticalValue, String supplierKey */
            registerProductBook(fields[1], Integer.parseInt(fields[6]), fields[3], fields[2], fields[4], Integer.parseInt(fields[8]), Integer.parseInt(fields[7]), fields[5]);
        } 
    }

    /**
     * Registers a client
     * @param key Client's key.
     * @param name Client's name.
     * @param address Client's address.
     * @throws ExistingClientKeyException
     */
    public void registerClient(String key, String name, String address) throws ExistingClientKeyException {
        if (_clients.containsKey(key)) {
            throw new ExistingClientKeyException(key);
        }
        _clients.put(key, new Client(key, name, address));
    }

    /**
     * Registers a supplier
     * @param key Supplier's key.
     * @param name Supplier's name.
     * @param address Supplier's address.
     * @throws ExistingSupplierKeyException
     */
    public void registerSupplier(String key, String name, String address) throws ExistingSupplierKeyException {
        if (_suppliers.containsKey(key)) {
            throw new ExistingSupplierKeyException(key);
        }
        _suppliers.put(key, new Supplier(key, name, address));
    }

    /**
     * Registers a box.
     * @param key Box's key
     * @param price Box's price
     * @param criticalValue Box's critical value
     * @param stock Box's stock
     * @param supplierKey Box's supplier key
     * @param serviceType Box's service type
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException
     * @throws InvalidServiceTypeException
     */
    public void registerProductBox(String key, int price, int criticalValue, int stock, String supplierKey, String serviceType) 
                                throws ExistingProductKeyException, NonExistingSupplierKeyException, InvalidServiceTypeException {
        if (_products.containsKey(key)) {
            throw new ExistingProductKeyException(key);
        }
        if (!_suppliers.containsKey(supplierKey)) {
            throw new NonExistingSupplierKeyException(supplierKey);
        }
         _products.put(key, new Box(key, price, criticalValue, stock, supplierKey, serviceType));
    }

    /**
     * Registers a box.
     * Defaults stock to 0.
     * @param key Box's key
     * @param price Box's price
     * @param criticalValue Box's critical value
     * @param supplierKey Box's supplier key
     * @param serviceType Box's service type
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException
     * @throws InvalidServiceTypeException
     */
    public void registerProductBox(String key, int price, int criticalValue, String supplierKey, String serviceType) 
                                throws ExistingProductKeyException, NonExistingSupplierKeyException, InvalidServiceTypeException {
        registerProductBox(key, price, criticalValue, 0, supplierKey, serviceType);
    }


    /**
     * Registers a container.
     * @param key Container's key
     * @param price Container's price
     * @param criticalValue Container's critical value
     * @param stock Container's stock
     * @param supplierKey Container's supplier key
     * @param serviceType Container's service type
     * @param serviceLevel Container's service level
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException
     * @throws InvalidServiceTypeException
     * @throws InvalidServiceLevelException
     */
    public void registerProductContainer(String key, int price, int criticalValue, int stock, String supplierKey, 
                                        String serviceType, String serviceLevel) throws ExistingProductKeyException, 
                                        NonExistingSupplierKeyException, InvalidServiceTypeException, InvalidServiceLevelException {
        if (_products.containsKey(key)) {
            throw new ExistingProductKeyException(key);
        }
        if (!_suppliers.containsKey(supplierKey)) {
            throw new NonExistingSupplierKeyException(supplierKey);
        }
         _products.put(key, new Container(key, price, criticalValue, stock, supplierKey, serviceType, serviceLevel));
    }

    /**
     * Registers a container.
     * Defaults stock to 0.
     * @param key Container's key
     * @param price Container's price
     * @param criticalValue Container's critical value
     * @param supplierKey Container's supplier key
     * @param serviceType Container's service type
     * @param serviceLevel Container's service level
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException
     * @throws InvalidServiceTypeException
     * @throws InvalidServiceLevelException
     */
    public void registerProductContainer(String key, int price, int criticalValue, String supplierKey, 
                                        String serviceType, String serviceLevel) throws ExistingProductKeyException, 
                                        NonExistingSupplierKeyException, InvalidServiceTypeException, InvalidServiceLevelException {
        registerProductContainer(key, price, criticalValue, 0, supplierKey, serviceType, serviceLevel);
    }

    /**
     * Registers a book.
     * @param key Book's key
     * @param price Book's price
     * @param author Book's author
     * @param title Book's title
     * @param ISBN Book's ISBN
     * @param stock Book's stock
     * @param criticalValue Book's critical value
     * @param supplierKey Book's supplier key
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException 
     */
    public void registerProductBook(String key, int price, String author, String title, String ISBN, int stock,
                                    int criticalValue, String supplierKey) throws ExistingProductKeyException, NonExistingSupplierKeyException {
        if (_products.containsKey(key)) {
            throw new ExistingProductKeyException(key);
        }                               
        if (!_suppliers.containsKey(supplierKey)) {
            throw new NonExistingSupplierKeyException(supplierKey);
        }
        _products.put(key, new Book(key, price, author, title, ISBN, stock, criticalValue, supplierKey));
    }

    /**
     * Registers a book.
     * Defaults stock to 0.
     * @param key Book's key
     * @param price Book's price
     * @param author Book's author
     * @param title Book's title
     * @param ISBN Book's ISBN
     * @param criticalValue Book's critical value
     * @param supplierKey Book's supplier key
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException 
     */
    public void registerProductBook(String key, int price, String author, String title, String ISBN, int criticalValue, 
                                    String supplierKey) throws ExistingProductKeyException, NonExistingSupplierKeyException {
        registerProductBook(key, price, author, title, ISBN, 0, criticalValue, supplierKey);
    }



    /* GETTERS */

    /**
     * Getter for client
     * @param key Client's key.
     * @return Client class associated with the key
     * @throws NonExistingClientKeyException
     * 
     */
    public Client getClient(String key) throws NonExistingClientKeyException {
        if (!_clients.containsKey(key)) {
            throw new NonExistingClientKeyException(key);
        }
        return _clients.get(key);
    }

    /**
     * Getter for all clients
     * @return a list containing all clients
     */
    public List<Client> getClients() {
        return new ArrayList<Client>(_clients.values());
    }

    /**
     * Getter for supplier
     * @param key Supplier's key.
     * @return Supplier class associated with the key
     * @throws NonExistingSupplierKeyException
     */
    public Supplier getSupplier(String key) throws NonExistingSupplierKeyException {
        if (!_suppliers.containsKey(key)) {
            throw new NonExistingSupplierKeyException(key);
        }
        return _suppliers.get(key);
    }

    /**
     * Getter for all suppliers
     * @return a list containing all suppliers
     */
    public List<Supplier> getSuppliers() {
        return new ArrayList<Supplier>(_suppliers.values());
    }
    
    /**
     * Getter for all products
     * @return a list containing all products
     */
    public List<Product> getProducts() {
        return new ArrayList<Product>(_products.values());
    }


}
