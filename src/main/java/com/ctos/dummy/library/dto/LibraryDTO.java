package com.ctos.dummy.library.dto;


public record LibraryDTO(
        String libraryName,
        java.util.List<AisleDTO> aisles
) {}

