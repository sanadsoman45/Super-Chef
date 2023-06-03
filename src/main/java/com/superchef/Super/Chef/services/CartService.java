package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.Cart;

import java.util.Set;

public interface CartService {

    Cart addItems(Cart cart, String emailid);

    void deleteItem(String ingName, String emailid);

    void deleteAllItems(String emailid);

    Set<Cart> getCartItemsByIngName(String emailid, String ingName);

    Set<Cart> getCartItemsByType(String emailid, String ingType);

    Set<Cart> getCartItems(String emailid);

    boolean checkIngAdded(String emailid, String ingName);

    Cart updateType(String ingName,String emailid);

}
