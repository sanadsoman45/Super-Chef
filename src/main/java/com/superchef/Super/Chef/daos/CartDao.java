package com.superchef.Super.Chef.daos;

import com.superchef.Super.Chef.entities.Cart;
import com.superchef.Super.Chef.entities.IngCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Integer> {
}
