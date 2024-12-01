package com.inpost.assessment.shoppingapp.rest.controllers;

import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.rest.controllers.exception.BadQueryArgumentException;
import com.inpost.assessment.shoppingapp.rest.data.TotalPriceDTO;
import com.inpost.assessment.shoppingapp.services.interfaces.PriceCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DiscountController {
    private final PriceCalculatorService priceCalculatorService;

    @GetMapping("/calculateDiscount")
    public TotalPriceDTO discountCalculation(@RequestParam(value = "itemUuid", defaultValue = "0") Long itemUuid,
                                             @RequestParam(value = "quantity", defaultValue = "0") int quantity,
                                             @RequestParam(value = "policy", defaultValue = "bad") String policy
                                     ) throws BadQueryArgumentException {
        try {
            return priceCalculatorService.calculatePrice(itemUuid, quantity, Policy.valueOf(policy.toUpperCase()));
        } catch (Exception e) {
            throw new BadQueryArgumentException(e.getMessage());
        }
    }
}
