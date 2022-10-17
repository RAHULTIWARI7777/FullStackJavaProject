package foodbox.Capstone1.global;

import java.util.ArrayList;
import java.util.List;

import foodbox.Capstone1.model.Product;

public class GlobalData {
   public static List<Product> cart;
    static {
    	cart = new ArrayList<Product>();
    }
    
}

