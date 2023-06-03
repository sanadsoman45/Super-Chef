package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.Exceptions.CartItemAlreadyExists;
import com.superchef.Super.Chef.Exceptions.CartItemNotFound;
import com.superchef.Super.Chef.Exceptions.IngredientNotFoundException;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.entities.Cart;
import com.superchef.Super.Chef.entities.IngCart;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/superchef")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/user/{emailid}")
    public Cart addItems(@RequestBody Cart cart,@PathVariable String emailid) {
        return cartService.addItems(cart, emailid);
    }

    @DeleteMapping("/user/{emailid}/cart/{ingName}")
    public void deleteItem(@PathVariable String ingName,@PathVariable String emailid) {
        cartService.deleteItem(ingName,emailid);
    }

    @DeleteMapping("/cart/{emailid}")
    public void deleteAllItems(@PathVariable String emailid){
        cartService.deleteAllItems(emailid);
    }

    @PutMapping("/cart/{ingName}/type/user/{emailid}")
    public Cart updateType(@PathVariable String ingName,@PathVariable String emailid){
        return cartService.updateType(ingName,emailid);
    }

    @GetMapping("/cart/{ingName}/user/{emailid}")
    public boolean checkIngAdded(@PathVariable String emailid,@PathVariable String ingName){
        return cartService.checkIngAdded(emailid,ingName);
    }

    @GetMapping("/cart/{emailid}")
    public Set<Cart> getCartItems(@PathVariable String emailid){
        return cartService.getCartItems(emailid);
    }

    @GetMapping("/cart/type/{ingType}/user/{emailid}")
    public Set<Cart> getCartItemsByType(@PathVariable String emailid,@PathVariable String ingType){
        return cartService.getCartItemsByType(emailid,ingType);
    }

    @GetMapping("/cart/items/{ingName}/user/{emailid}")
    public Set<Cart> getCartItemsByIngName(@PathVariable  String emailid,@PathVariable String ingName){
        return cartService.getCartItemsByIngName(emailid,ingName);
    }


}
