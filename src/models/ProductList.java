package models;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public void printInfo() {
        System.out.println("-----" + products.get(0).getType() + "-----");
        for (Product p : products) {
            p.printInfo();
        }
    }
}
