package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@JsonIgnoreProperties(value = {"id", "prixAchat"})
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;

    @length(min=10, max=50000)
    private int prix;

    @Min(value = 1)
    private int prixAchat;

    //constructeur défaut
    public Product(){

    }

    public Product(String nom, int prix, int prixAchat) {
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }
}
