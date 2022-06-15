package com.bext.onetooneunidirection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountryCapital {
    private String countryName;
    private String capitalName;
}
