package com.example.skicurort.exception;

public class NoIdException extends RuntimeException {

  public NoIdException(Long id) {

    super("Could not find object with id: " + id);
  }
}
