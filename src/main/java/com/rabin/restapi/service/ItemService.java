package com.rabin.restapi.service;

import com.rabin.restapi.OutOfLengthException;
import com.rabin.restapi.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    public List<Item> itemList = new ArrayList<>();
    private Long itemId = 1L;

    public List<Item> getAllItems() {
        return itemList;
    }

    public Item getItemById(Long id) {
        return itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Item createItem(Item item) {
       /* item.setId(itemId++);
        itemList.add(item);
        return item;*/
        if (item.getName() =="") {
            throw new OutOfLengthException("Null Value is not accepted");
        }

        if (item.getName() != null && item.getName().length() <= 20) {
            item.setId(itemId++);
            itemList.add(item);
            return item;
        } else {
            throw new OutOfLengthException ("Name too lengthy...");
        }
    }
    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = getItemById(id);
        if (existingItem != null) {
            existingItem.setName(updatedItem.getName());
        }

        return existingItem;
    }
    public void deleteItem(Long id) {
        itemList.removeIf(item -> item.getId().equals(id));
    }
}
