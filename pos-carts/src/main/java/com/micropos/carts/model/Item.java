package com.micropos.carts.model;

import java.io.Serializable;
import com.micropos.products.model.*;

public class Item implements Serializable {
    private Product product;
    private int quantity;

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setProduct(Product p) {
        this.product = p;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}