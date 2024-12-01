package com.inpost.assessment.shoppingapp.services;

import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.persistence.entity.Item;
import com.inpost.assessment.shoppingapp.services.interfaces.DiscountCalculatorService;
import com.inpost.assessment.shoppingapp.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiscountCalculatorServiceTest {

    @MockitoBean
    private final ItemService itemService;

    private final DiscountCalculatorService discountCalculatorService;

    @BeforeAll
    public static void setUp() {}

    @AfterAll
    public static void tearDown() {}

    @Test
    public void withDefaultConfigValuesTotalAmountDiscountShouldEqual5For10Items() {
        Mockito.when(itemService.getBasePrice(any())).thenReturn(1000000.00);

        double amountDiscount = discountCalculatorService.calculateDiscount(1234L, 10, Policy.AMOUNT);

        assert(amountDiscount == 50.00);
    }

    @Test
    public void whenItemBatchIsLargeTotalAmountDiscountShouldNotExceedMaxValue() {
        Mockito.when(itemService.getBasePrice(any())).thenReturn(100.00);

        double amountDiscount = discountCalculatorService.calculateDiscount(1234L, 1000000, Policy.AMOUNT);

        assert(amountDiscount == 95.00 * 100000000);
    }

    @Test
    public void whenItemBatchIsSmallerThanItemIncrementDiscountShouldBe0() {
        Mockito.when(itemService.getBasePrice(any())).thenReturn(1000000.00);

        double amountDiscount = discountCalculatorService.calculateDiscount(1234L, 1, Policy.AMOUNT);

        assert(amountDiscount == 0);
    }

    @Test
    public void whenQuantityProvidedIsNegativeAnExceptionShouldBeThrown() {
        Mockito.when(itemService.getBasePrice(any())).thenReturn(10000.00);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            discountCalculatorService.calculateDiscount(1234L, -100, Policy.AMOUNT);
        });

        String expectedMessage = "Quantity must be greater than 0";
        String actualMessage = exception.getMessage();

        assert(actualMessage.contains(expectedMessage));
    }
}
