package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.Storefront;
import woo.Product;  
import woo.ProductByKeyComparator; 

import java.util.List;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {

  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws DialogException {
    List<Product> productsSortedByKey = _receiver.getProducts();
    productsSortedByKey.sort(new ProductByKeyComparator());
    for (Product p: productsSortedByKey) {
      _display.addLine(p.toString());
    }
    _display.display();
  }

}
