package com.inpost.assessment.shoppingapp.services;

import com.inpost.assessment.shoppingapp.configs.AmountPolicyConfiguration;
import com.inpost.assessment.shoppingapp.configs.DiscountCalculatorConfiguration;
import com.inpost.assessment.shoppingapp.configs.PercentagePolicyConfiguration;
import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.services.interfaces.DiscountCalculatorService;
import com.inpost.assessment.shoppingapp.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DiscountCalculatorServiceServiceImpl implements DiscountCalculatorService {

    private final AmountPolicyConfiguration amountPolicyConfiguration;
    private final PercentagePolicyConfiguration percentagePolicyConfiguration;
    private final ItemService itemService;

    @Override
    public double calculateDiscount(Long itemUuid, int quantity, Policy policy) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        double itemBasePrice = itemService.getBasePrice(itemUuid);
        return policy == Policy.AMOUNT ? calculateAmountDiscount(itemBasePrice, quantity) : calculatePercentageDiscount(itemBasePrice, quantity);
    }

    private double calculateAmountDiscount(double itemBasePrice, int quantity) {
        double itemDiscount = Math.min(calculateIncrement(quantity, amountPolicyConfiguration)*amountPolicyConfiguration.getDiscountIncrement(),
                amountPolicyConfiguration.getDiscountLimit()*itemBasePrice);
        return itemDiscount*quantity;
    }

    private double calculatePercentageDiscount(double itemBasePrice, int quantity) {
        double itemDiscount = Math.min(itemBasePrice*calculateIncrement(quantity, percentagePolicyConfiguration)*amountPolicyConfiguration.getDiscountIncrement()/100,
                percentagePolicyConfiguration.getDiscountLimit()*itemBasePrice);
        return itemDiscount*quantity;
    }

    private int calculateIncrement(int quantity, DiscountCalculatorConfiguration configuration) {
        int increment = configuration.getItemAmountIncrement();
        switch(configuration.getIncrementScale()){
            case EXPONENTIAL -> {
                int ret = 0;
                for(int i = increment; i <= quantity; i*= increment){
                    ++ret;
                }
                return ret;
            }
            case LINEAR -> {
                return Math.floorDiv(quantity, increment);
            }
            default -> { return 0;}
        }
    }
}

