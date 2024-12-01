package com.inpost.assessment.shoppingapp.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalPriceDTO {
    private Long itemUuid;
    private Integer quantity;
    private Double itemBasePrice;
    private Double totalBasePrice;
    private Double totalDiscount;
    private Double totalPrice;
}
