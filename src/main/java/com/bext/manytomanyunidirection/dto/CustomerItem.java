package com.bext.manytomanyunidirection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerItem {
    private Long customerId;
    private String customerName;
    private Long itemId;
    private String itemName;
}
