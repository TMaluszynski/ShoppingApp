package com.inpost.assessment.shoppingapp.e2e;

import com.inpost.assessment.shoppingapp.enums.Policy;
import com.inpost.assessment.shoppingapp.rest.data.TotalPriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingAppE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testShoppingAppReturnsAppropriateDataPackage() {
        TotalPriceDTO expected = new TotalPriceDTO(
                1L,
                100,
                100D,
                10000D,
                1000D,
                9000D
        );

        TotalPriceDTO result = restTemplate.getForObject("/calculateDiscount?itemUuid={itemUuid}&quantity={quantity}&policy={policy}", TotalPriceDTO.class, String.valueOf(1L), String.valueOf(100), String.valueOf(Policy.PERCENTAGE));

        assert(Objects.equals(result, expected));
    }
}
