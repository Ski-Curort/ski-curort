package com.example.skicurort.equipment;

import com.sun.istack.NotNull;

import java.math.BigDecimal;

public record EquipmentDto(
        Long id,

        @NotNull
        String type,

        @NotNull
        String mark,
        boolean available,
        @NotNull BigDecimal cost)
{}