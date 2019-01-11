package com.ecommerce.microcommerce.dao;
import com.ecommerce.microcommerce.model.Product;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product(1, "PC portable", 600));
        productList.add(new Product(2, "Telephone", 460));
        productList.add(new Product(3, "CPU", 350));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {

        for(Product product : productList)
        {
            if(product.getId() == id)
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product findByName(String name) {

        for(Product product : productList)
        {
            if(product.getNom() == name)
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        productList.add(product);
        return product;
    }
}
