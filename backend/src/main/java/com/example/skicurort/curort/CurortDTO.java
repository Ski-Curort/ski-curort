package com.example.skicurort.curort;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public record CurortDTO(
    @Max(value = 100, message = "Name should not be greater than 100")
        @NotNull(message = "Name cannot be empty")
        String curortName,
    @NotNull(message = "Address cannot be empty") String curortAdress,
    @Email(message = "Email invalid") String currortEmail,
    @NotNull(message = "Phone number cannot be empty") Long curortPhonenumber) {}
