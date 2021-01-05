package com.vijfmusketiers.ProjectB.rollator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RollatorNotFound extends RuntimeException {

    public RollatorNotFound(@PathVariable Long productId) {
        super("Product not found with id: " + productId);
    }

}
