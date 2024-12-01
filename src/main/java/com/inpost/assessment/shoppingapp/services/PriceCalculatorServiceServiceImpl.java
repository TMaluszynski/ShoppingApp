package com.inpost.assessment.shoppingapp.services;

import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.rest.data.TotalPriceDTO;
import com.inpost.assessment.shoppingapp.services.interfaces.DiscountCalculatorService;
import com.inpost.assessment.shoppingapp.services.interfaces.ItemService;
import com.inpost.assessment.shoppingapp.services.interfaces.PriceCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceCalculatorServiceServiceImpl implements PriceCalculatorService {

    private final DiscountCalculatorService discountCalculatorService;
    private final ItemService itemService;

    @Override
    public TotalPriceDTO calculatePrice(Long uuid, int quantity, Policy policy) {
        if(quantity<=0)
            throw new IllegalArgumentException("Quantity must be greater than 0");
        double itemBasePrice = itemService.getBasePrice(uuid);
        double totalBasePrice = itemBasePrice * quantity;
        double totalDiscount = discountCalculatorService.calculateDiscount(uuid, quantity, policy);
        return  new TotalPriceDTO(
                uuid,
                quantity,
                itemBasePrice,
                totalBasePrice,
                totalDiscount,
                totalBasePrice - totalDiscount
        );
    }
}
