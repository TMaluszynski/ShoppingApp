package com.inpost.assessment.shoppingapp.services;

import com.inpost.assessment.shoppingapp.persistence.ItemRepository;
import com.inpost.assessment.shoppingapp.persistence.entity.Item;
import com.inpost.assessment.shoppingapp.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemServiceAndRepoTest {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Test
    void shouldLoadDataProperly() {
        List<Item> items = itemRepository.findAll();
        assert(items.size()==5);;
    }

    @Test
    void shouldLoadBasePriceCorrectly() {
        double basePriceOfItem1 = 100.00;
        double retrievedPriceOfItem1 = itemService.getBasePrice(1L);
        assert(retrievedPriceOfItem1==basePriceOfItem1);
    }
}
