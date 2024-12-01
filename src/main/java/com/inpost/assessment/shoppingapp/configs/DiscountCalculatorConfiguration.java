package com.inpost.assessment.shoppingapp.configs;

import com.inpost.assessment.shoppingapp.enums.IncrementScale;
import lombok.Data;

@Data
public class DiscountCalculatorConfiguration {
    int itemAmountIncrement;
    int discountIncrement;
    int discountLimit;
    IncrementScale incrementScale;
}
