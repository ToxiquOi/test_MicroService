package com.ecommerce.microcommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ProduitNonSupprimer extends Throwable {
    public ProduitNonSupprimer(String s) {
    }
}
