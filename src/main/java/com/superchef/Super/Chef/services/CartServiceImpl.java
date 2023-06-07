package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.CartItemAlreadyExists;
import com.superchef.Super.Chef.Exceptions.CartItemNotFound;
import com.superchef.Super.Chef.Exceptions.IngredientNotFoundException;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.daos.CartDao;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.Cart;
import com.superchef.Super.Chef.entities.IngCart;
import com.superchef.Super.Chef.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements  CartService{

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IngCart ingCart;

    @Override
    public Cart addItems(Cart cart,String emailid) {
        Optional<User> result = userDao.findById(emailid);
        Cart item = null;
        if(result.isPresent()){
            User user = result.get();
            for(IngCart mapping:user.getUseringCart()){
                if(mapping.getCart().getIngName().equalsIgnoreCase(cart.getIngName())){
                    throw new CartItemAlreadyExists("Item Already Added in the cart.");
                }
            }
            item = cartDao.save(cart);
            ingCart.setCart(item);
            ingCart.setUser(user);
            Set<IngCart> ingSet = new HashSet<>();
            ingSet.add(ingCart);
            user.setUseringCart(ingSet);
            userDao.save(user);
        }
        else{
            throw new userNotFound("User Not Found.");
        }
        return item;
    }

    @Override
    public void deleteItem(String ingName, String emailid) {
        Optional<User> result = userDao.findById(emailid);
        int id =0;
        if(result.isPresent()){
            User user = result.get();
            for(IngCart mapping: user.getUseringCart()){
                if(mapping.getCart().getIngName().equalsIgnoreCase(ingName)){
                    id = mapping.getCart().getId();
                    user.getUseringCart().remove(mapping);
                }
            }
            if(id==0){
                throw new CartItemNotFound("Item Not Found In Cart.");
            }
            cartDao.deleteById(id);
        }
        else{
            throw new userNotFound("User not Found");
        }
    }

    public void deleteAllItems(String emailid){
        Optional<User> result = userDao.findById(emailid);
        Set<Integer> cart = new HashSet<>();
        if(result.isPresent()){
            User user = result.get();
            for(IngCart mapping:user.getUseringCart()){
                cart.add(mapping.getCart().getId());
            }
            if(cart.size()==0){
                throw new CartItemNotFound("Cart Item Not Found.");
            }
            user.getUseringCart().clear();
            cartDao.deleteAllById(cart);

        }
        else{
            throw new userNotFound("User not Found.");
        }
    }

    public Cart updateType(String ingName,String emailid){
        Optional<User> result = userDao.findById(emailid);
        Cart item = null;
        if(result.isPresent()){
            User user = result.get();
            for(IngCart mapping:user.getUseringCart()){
                if(mapping.getCart().getIngName().equalsIgnoreCase(ingName)){
                    item = mapping.getCart();
                    if(mapping.getCart().getIngType().equalsIgnoreCase("list")){
                        mapping.getCart().setIngType("got");
                        mapping.setCart(mapping.getCart());
                    }
                    else{
                        mapping.getCart().setIngType("list");
                        mapping.setCart(mapping.getCart());
                    }
                    break;
                }
            }
            if(item == null){
                throw new IngredientNotFoundException("Ingredient Not Found");
            }
            item = cartDao.save(item);

        }
        else{
            throw new userNotFound("User Not Found");
        }
        return item;
    }

    public boolean checkIngAdded(String emailid, String ingName){
        Optional<User> result = userDao.findById(emailid);
        if(result.isPresent()){
            User user = result.get();
            for(IngCart cart:user.getUseringCart()){
                if(cart.getCart().getIngName().equalsIgnoreCase(ingName)){
                    return true;
                }
            }
        }
        else{
            throw new userNotFound("User Not Found");
        }
        return false;
    }

    public Set<Cart> getCartItems(String emailid){
        Optional<User> result = userDao.findById(emailid);
        Set<Cart> ingCart = new HashSet<>();
        if(result.isPresent()){
            User user = result.get();
            for(IngCart cart:user.getUseringCart()){
                ingCart.add(cart.getCart());
            }
            if(ingCart.size()==0){
                throw new IngredientNotFoundException("No Ingredients Found");
            }
        }
        else{
            throw new userNotFound("User Not Found");
        }
        return ingCart;
    }

    public Set<Cart> getCartItemsByType(String emailid, String ingType){
        Optional<User> result = userDao.findById(emailid);
        Set<Cart> ingCart = new HashSet<>();
        if(result.isPresent()){
            User user = result.get();
            for(IngCart cart: user.getUseringCart()){
                if(cart.getCart().getIngType().equalsIgnoreCase(ingType)){
                    ingCart.add(cart.getCart());
                }
            }
            if(ingCart.size()==0){
                throw new IngredientNotFoundException("Ingredients Not Found");
            }
        }
        else{
            throw new userNotFound("User Not Found");
        }
        return ingCart;
    }

    public Set<Cart> getCartItemsByIngName(String emailid, String ingName){
        Optional<User> result = userDao.findById(emailid);
        Set<Cart> ingCart = new HashSet<>();
        if(result.isPresent()){
            User user = result.get();
            for(IngCart cart:user.getUseringCart()){
                if(cart.getCart().getIngName().equalsIgnoreCase(ingName)){
                    ingCart.add(cart.getCart());
                }
            }
            if(ingCart.size()==0){
                throw new IngredientNotFoundException("Ingredient Not Found");
            }
        }
        else{
            throw new userNotFound("User Not Found");
        }
        return ingCart;
    }




}
