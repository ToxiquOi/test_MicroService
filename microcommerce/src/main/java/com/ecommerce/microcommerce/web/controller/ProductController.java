package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDaoImpl;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductDaoImpl productDao;

    @GetMapping(value = "/Produits")
    public List<Product> listeProduits(){

        return productDao.findAll();
    }


    @GetMapping(value = "/Produits/Nom/{name}")
    public Product PrintProductByName(@PathVariable String name){

        return productDao.findByName(name);
    }


    @GetMapping(value = "/Produits/{id}")
    public Product PrintProductById(@PathVariable int id){

        return productDao.findById(id);
    }

    @PostMapping(value = "/Produits")
    public void ajouterProduit(@RequestBody Product product){
        productDao.save(product);
    }

}

