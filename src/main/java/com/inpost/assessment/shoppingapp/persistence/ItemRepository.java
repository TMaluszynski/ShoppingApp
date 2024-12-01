package com.inpost.assessment.shoppingapp.persistence;

import com.inpost.assessment.shoppingapp.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item getItemById(Long uuid);
}

