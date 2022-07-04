package com.micropos.carts.rest;

import com.micropos.carts.api.*;
import com.micropos.carts.dto.*;
import com.micropos.carts.mapper.ItemMapper;
import com.micropos.carts.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@EnableDiscoveryClient
public class CartController implements CartApi {

    private final ItemMapper itemMapper;

    private final CartService cartService;

    public CartController(CartService cartService, ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
        this.cartService = cartService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ItemDto>> listItems() {
        List<ItemDto> items = new ArrayList<>(itemMapper.toItemsDto(this.cartService.getItems()));
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/{productId}")
    public ResponseEntity<List<ItemDto>> putProductById(String productId) {
        boolean flag = cartService.putItem(productId);
        List<ItemDto> items = new ArrayList<>(itemMapper.toItemsDto(this.cartService.getItems()));
        if (flag)
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(items, HttpStatus.NOT_FOUND);
    }
}
