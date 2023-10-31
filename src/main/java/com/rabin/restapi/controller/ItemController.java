package com.rabin.restapi.controller;

import com.rabin.restapi.OutOfLengthException;
import com.rabin.restapi.model.Item;
import com.rabin.restapi.model.Response;
import com.rabin.restapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;



    @GetMapping
    public List<Item>getAllItems(){
        return  itemService.getAllItems();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item>getItemById(@PathVariable Long id){
        Item item = itemService.getItemById(id);
        if (item != null){
            return ResponseEntity.ok(item);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<Item>createItem(@RequestBody Item item){
        try {
            Item createdItem = itemService.createItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        }
        catch (OutOfLengthException e){
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody Item item) {

        Response res = new Response();
        Item updatedItem = itemService.updateItem(id, item);
        if (updatedItem != null) {
            res.setMessage("Successfully updated");
            res.setStatusCode("200");
            res.setResponseBody(updatedItem);
            return ResponseEntity.ok(res);
        }
        else {
            res.setMessage("Could not Update");
            res.setStatusCode("100");
            res.setResponseBody(updatedItem);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
//        Response res = new Response();
        itemService.deleteItem(id);
     /*   res.setMessage("Successfully deleted");
        res.setStatusCode("200");
        res.setResponseBody(deleteItem(id));*/
        return ResponseEntity.noContent().build();
    }
}






