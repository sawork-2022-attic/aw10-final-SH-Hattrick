package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private Cart singleton_cart;

    public CartServiceImpl(){
        this.singleton_cart=new Cart();
    }

    @Override
    public Cart cart() {
        return this.singleton_cart;
    }

    @Override
    public List<Item> getItems() {
        return singleton_cart.getItems();
    }

    @Override
    public Item getItem(String id) {
        for (Item i : singleton_cart.getItems())
            if (i.getProduct().getId().equals(id))
                return i;
        return null;
    }

    @Override
    public boolean putItem(String id) {
        return this.singleton_cart.addItem(id);
    }

}
