package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;


@RestController
public class ProductController {

    @Autowired ProductDao productDao;

    @GetMapping(value = "/Produits")
    public List<Product> listeProduits(){

        return productDao.findAll();
    }


    @GetMapping(value = "/Produits/Nom/{name}")
    public Product PrintProductByName(@PathVariable String name){

        return productDao.findByNom(name);
    }


    @GetMapping(value = "/Produits/{id}")
    public Product PrintProductById(@PathVariable int id) throws ProduitIntrouvableException {
        Product product = productDao.findById(id);

        if(product == null) throw new ProduitIntrouvableException("le produit avec l'id : "+id+"n'existe pas !");
        else
            return product;
    }


    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product){

        Product productSaved = productDao.save(product);

        if(productSaved == null)
        {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productSaved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/Produits/{id}")
    public void supprimerProduit(@PathVariable int id){
        productDao.deleteById(id);
    }



}

