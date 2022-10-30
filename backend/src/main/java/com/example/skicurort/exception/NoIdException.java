package com.example.skicurort.exception;

import java.util.function.Supplier;

public class NoIdException extends RuntimeException      {



    public NoIdException(Long id) {

        super("Could not find object with id: " + id);

    }

}
