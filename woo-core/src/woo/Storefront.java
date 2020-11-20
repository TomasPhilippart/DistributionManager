package woo;

import java.io.*;
import java.util.List;
import woo.exceptions.*;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

	/** Current filename. */
	private String _filename = "";

	/** File status */
	private boolean _fileModified = true;

	/** The actual store. */
	private Store _store = new Store();

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws MissingFileAssociationException
	 */
	public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
		if (_fileModified) {
			if (_filename.equals("")) {
				throw new MissingFileAssociationException();
			} else {
				_fileModified = false;
				ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
				out.writeObject(_store);
				out.close();
			}
		}
	}

	/**
	 * @param filename
	 * @throws MissingFileAssociationException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
		_filename = filename;
		save();
	}

	/**
	 * @param filename
	 * @throws UnavailableFileException
	 */
	public void load(String filename) throws ClassNotFoundException, UnavailableFileException, IOException {
		_filename = filename;
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(_filename)));
		_store = (Store) in.readObject();
		in.close();
	}

	/** 
     * Getter for current date
     * @return Current date
     */ 
	public int getCurrentDate() { return _store.getCurrentDate(); }
	
	/**
     * Advances date according to user input
     * @param numberOfDays
     * @throws InvalidNumberOfDaysException
     */
    public void advanceDate(int numberOfDays) throws InvalidNumberOfDaysException {
		_store.advanceDate(numberOfDays);
		_fileModified = true;
	}
	
	/**
	 * @param textfile
	 * @throws ImportFileException
	 */
	public void importFile(String textfile) throws ImportFileException {
		try {
			_store.importFile(textfile);
		} catch (IOException | BadEntryException | ExistingClientKeyException | ExistingSupplierKeyException | 
		        ExistingProductKeyException | InvalidServiceLevelException| InvalidServiceTypeException | NonExistingSupplierKeyException e) {
			throw new ImportFileException(textfile);
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
		_store.registerClient(key, name, address);
		_fileModified = true;

		
	}

	/**
	 * Registers a supplier
	 * @param key Supplier's key
	 * @param name Supplier's name
	 * @param address Supplier's address
	 * @throws ExistingSupplierKeyException
	 */
	public void registerSupplier(String key, String name, String address) throws ExistingSupplierKeyException {
		_store.registerSupplier(key, name, address);
		_fileModified = true;
	}

	/**
     * Registers a book
     * @param key Book's key
     * @param price Book's price
     * @param author Book's author
     * @param title Book's title
     * @param ISBN Book's ISBN
     * @param criticalValue Book's critical value
	 * @param criticalValue Book's supplierKey
     */
	public void registerProductBook(String key, int price, String author, String title, String ISBN, int criticalValue,
									String supplierKey) throws ExistingProductKeyException, NonExistingSupplierKeyException {
		_store.registerProductBook(key, price, author, title, ISBN, criticalValue, supplierKey);
		_fileModified = true;
	}

	/**
     * Registers a box
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
		_store.registerProductBox(key, price, criticalValue, supplierKey, serviceType);
		_fileModified = true;
	}

	/**
     * Registers a box
     * @param key Box's key
     * @param price Box's price
     * @param criticalValue Box's critical value
     * @param supplierKey Box's supplier key
     * @param serviceType Box's service type
     * @param serviceLevel Box's service level
     * @throws ExistingProductKeyException
     * @throws NonExistingSupplierKeyException
     * @throws InvalidServiceTypeException
     * @throws InvalidServiceLevelException
     */
	public void registerProductContainer(String key, int price, int criticalValue, String supplierKey, 
                                        String serviceType, String serviceLevel) throws ExistingProductKeyException, 
                                        NonExistingSupplierKeyException, InvalidServiceTypeException, InvalidServiceLevelException {
		_store.registerProductContainer(key, price, criticalValue, supplierKey, serviceType, serviceLevel);	
		_fileModified = true;								
	}
	
	/**
     * Getter for client
     * @param key Client's key.
     * @return Client class associated with the key
     * @throws NonExistingClientKeyException
     * 
     */
    public Client getClient(String key) throws NonExistingClientKeyException {
		return _store.getClient(key);
	}
	
	/**
	 * Getter for all clients
	 * @return a list containing all clients
	 */
	public List<Client> getClients() {
		return _store.getClients();
	}
	
	/**
	 * Getter for all suppliers
	 * @return a list containing all suppliers
	 */
	public List<Supplier> getSuppliers() {
		return _store.getSuppliers();
	}

	/**
	 * Getter for all products
	 * @return a list containing all products
	 */
	public List<Product> getProducts() {
		return _store.getProducts();
	}

}
