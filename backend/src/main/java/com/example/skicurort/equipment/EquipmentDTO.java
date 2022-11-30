package com.example.skicurort.equipment;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public record EquipmentDTO(
        Long id,
    @Max(value = 100, message = "type should not be greater than 100")
        @NotNull(message = "type of equipment shouldn't be empty")
        String type,
    @Max(value = 100, message = "mark should not be greater than 100")
        @NotNull(message = "mark of equipment shouldn't be empty")
        String size,
    @Max(value = 100, message = "mark should not be greater than 100")
        @NotNull(message = "mark of equipment shouldn't be empty")
        String mark,
    boolean available,
    @NotNull(message = "cost of equipment shouldn't be empty") BigDecimal cost) {}
