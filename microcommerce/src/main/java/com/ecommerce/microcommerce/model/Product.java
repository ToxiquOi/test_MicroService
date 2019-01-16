package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.*;

//@JsonIgnoreProperties(value = {"id"})
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    private String nom;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prixAchat=" + prixAchat +
                '}';
    }
}
