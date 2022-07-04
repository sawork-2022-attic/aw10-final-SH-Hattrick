package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;

import java.util.List;

public interface CartService {

    public Cart cart();

    public List<Item> getItems();

    public Item getItem(String id);

    public boolean putItem(String id);
}
