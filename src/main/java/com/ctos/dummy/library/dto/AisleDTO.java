package com.ctos.dummy.library.dto;

public record AisleDTO(
        String aisleName,
        java.util.List<BookDTO> books
) {}
