package com.inpost.assessment.shoppingapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inpost.assessment.shoppingapp.persistence.ItemRepository;
import com.inpost.assessment.shoppingapp.persistence.entity.Item;
import com.inpost.assessment.shoppingapp.services.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final String dataFilePath;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,@Value("${data.file-path}") String dataFilePath) throws IOException {
        this.itemRepository = itemRepository;
        this.dataFilePath = dataFilePath;
        initBasicDataFromFile();
    }

    @Override
    public double getBasePrice(Long id) {
        return itemRepository.getItemById(id).getBasePrice();
    }

    private void initBasicDataFromFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(dataFilePath)));
        ObjectMapper objectMapper = new ObjectMapper();
        Item[] jsonArray = objectMapper.readValue(content, Item[].class);
        itemRepository.saveAll(Arrays.asList(jsonArray));
    }
}
