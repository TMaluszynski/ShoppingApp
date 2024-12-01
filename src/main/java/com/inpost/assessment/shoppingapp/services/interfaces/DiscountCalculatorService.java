package com.inpost.assessment.shoppingapp.services.interfaces;

import com.inpost.assessment.shoppingapp.enums.Policy;

public interface DiscountCalculatorService {
    double calculateDiscount(Long itemUuid, int quantity, Policy policy);
}
