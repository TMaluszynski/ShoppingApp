package com.inpost.assessment.shoppingapp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "policy.amount")
public class AmountPolicyConfiguration extends DiscountCalculatorConfiguration{

}
