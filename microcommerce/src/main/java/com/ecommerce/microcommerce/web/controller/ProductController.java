package com.ecommerce.microcommerce.web.controller;

import java.util.*;
import java.net.URI;
import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.exceptions.ExceptionProduitGratuit;
import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.exceptions.ProduitNonSupprimer;
import com.ecommerce.microcommerce.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;

@Api(description = "API pour les opération CRUD sur les produits.")
@RestController
public class ProductController {

    @Autowired ProductDao productDao;


    @ApiOperation(value = "Récupère la liste complète des produits")
    @GetMapping(value = "/Produits")
    public List<Product> listeProduits(){

        return productDao.findAll();
    }



    @ApiOperation(value = "Récupère un produit grâce à son NOM à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Produits/Nom/{name}")
    public Product PrintProductByName(@PathVariable String name){

        return productDao.findByNom(name);
    }



    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Produits/{id}")
    public Product PrintProductById(@PathVariable int id) throws ProduitIntrouvableException {
        Product product = productDao.findById(id);

        if(product == null) throw new ProduitIntrouvableException("le produit avec l'id : "+id+"n'existe pas !");
        else
            return product;
    }



    @ApiOperation(value = "Ajoute un produit grâce à la base de donnée!")
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) throws ExceptionProduitGratuit {

        if(product.getPrix() <= 0) {
            throw new ExceptionProduitGratuit("le produit que vous tenter d'ajouter ne peut pas etre gratuit il vaut au minimum 1");
        }
        else {
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

    }



    @ApiOperation(value = "Supprime un produit grâce à son ID à condition que celui-ci soit en stock!")
    @DeleteMapping(value = "/Produits/{id}")
    public void supprimerProduitAvecId(@PathVariable int id){
        productDao.deleteById(id);
    }



    @ApiOperation(value = "Récupère un produit grâce à son NOM à condition que celui-ci soit en stock!")
    @DeleteMapping(value = "/Produit/{name}")
    public  void supprimerProduitAvecNom(@PathVariable String name) throws ProduitNonSupprimer {

        productDao.deleteByNom(name);
        if(productDao.findByNom(name) != null){
            throw new ProduitNonSupprimer("le produit : " + name + " n'a pas été supprimée !");
        }

    }



    @ApiOperation(value = "calcule la difference entre le prix et le prix d'achat pour tout les Produits")
    @GetMapping(value = "/AdminProduits")
    public List<String> calculerMargeProduit(){

        List<Product> listProduit = productDao.findAll();
        List<String> listAvecDifProduit = new ArrayList<>();

        for(Product product : listProduit){
            int dif = product.getPrix() - product.getPrixAchat();

            listAvecDifProduit.add(product.toString() + " la différence entre le prix et le prix d'achat : " + dif);
        }

        return listAvecDifProduit;
    }



    @ApiOperation(value = "Affiche tout les produits par ordre alphabétique")
    @GetMapping(value = "/Produits/Nom")
    public List<Product> trierProduitsParOrdreAlphabetique(){
        return  productDao.findAllByOrderByNom();
    }

}

