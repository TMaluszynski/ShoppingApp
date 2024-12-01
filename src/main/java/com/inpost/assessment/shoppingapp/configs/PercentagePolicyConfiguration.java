package com.inpost.assessment.shoppingapp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "policy.percentage")
public class PercentagePolicyConfiguration extends DiscountCalculatorConfiguration{

}
