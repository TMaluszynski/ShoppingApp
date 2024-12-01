package com.inpost.assessment.shoppingapp.services.interfaces;

import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.rest.data.TotalPriceDTO;

public interface PriceCalculatorService {
    TotalPriceDTO calculatePrice(Long itemUuid, int quantity, Policy policy);
}
