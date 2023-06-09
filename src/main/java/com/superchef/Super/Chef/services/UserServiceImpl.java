package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.UserEmailAlreadyExists;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.IngCart;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.entities.UserIng;
import com.superchef.Super.Chef.entities.User_Fav_Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;


    @Override
    public User createUser(User user) {
        boolean userExists = userDao.findById(user.getUserEmail()).isPresent();
        if(userExists == true){
            throw new UserEmailAlreadyExists("User Email Already Exists");
        }
        return userDao.save(user);

    }

    @Override
    public List<User> getAllUsers() {

        List<User> user = userDao.findAll();
        if(user.size() == 0){
            throw new userNotFound("No records Found to display.");
        }
        return user;

    }

    @Override
    public HashMap<String,String> getUserByEmailId(String emailid) {

        Optional<User> result = userDao.findById(emailid);
        HashMap<String,String> user = new HashMap<>();
        if(result.isPresent()){
          user.put("userEmail",result.get().getUserEmail());
          user.put("userName",result.get().getUserName());
        }
        else{
            throw new userNotFound("User Not Found for "+emailid);
        }
        return user;

    }

    public boolean checkUserByEmailId(String emailId){

        return userDao.findById(emailId).isPresent();

    }

    @Override
    @Transactional
    public int updateUser(User user, String emailid) {
        Optional<User> result = userDao.findById(emailid);
        User localUser = null;
        int updateCount;

        if(!result.isPresent()){
           throw new userNotFound("User Not Found for "+emailid);
        }
        else if(user.getUserEmail().equalsIgnoreCase(emailid)){
            updateCount = userDao.updateUser(user.getUserEmail(),user.getUserName(),user.getPasswd(),emailid);
            if(updateCount==1){
                localUser = result.get();
                Set<User_Fav_Recipes> mapping = localUser.getUserMapping();
                for(User_Fav_Recipes mapuser:mapping){
                    mapuser.setUser(user);
                }
                for(UserIng mapIng: localUser.getUseringMapping()){
                    mapIng.setUser(user);
                }
                for(IngCart mapCart: localUser.getUseringCart()){
                    mapCart.setUser(user);
                }
            }
        }
        else if(userDao.findById(user.getUserEmail()).isPresent()){
            throw new UserEmailAlreadyExists("User Email-Id Already exists.");
        }
        else{
            updateCount = userDao.updateUser(user.getUserEmail(),user.getUserName(),user.getPasswd(),emailid);
            if(updateCount==1){
                localUser = result.get();
                Set<User_Fav_Recipes> mapping = localUser.getUserMapping();
                for(User_Fav_Recipes mapuser:mapping){
                    mapuser.setUser(user);
                }
                for(UserIng mapIng: localUser.getUseringMapping()){
                    mapIng.setUser(user);
                }
                for(IngCart mapCart: localUser.getUseringCart()){
                    mapCart.setUser(user);
                }
            }
        }

        return updateCount;
    }

    @Override
    public String loginUser(String emailid, String passwd) {
        User user = userDao.findByuserEmailAndPasswd(emailid,passwd);
        if(user == null){
            throw new userNotFound("User Details Not Found.");
        }
        return "User Login Successful";
    }

    @Override
    public boolean deleteUser(String emailid) {

        boolean check = true;
        Optional<User> result = userDao.findById(emailid);
        if(!result.isPresent()){
            check = false;
            throw new userNotFound("User Not Found "+emailid);
        }
        else{
            User user = result.get();
            for(User_Fav_Recipes mappings: user.getUserMapping()){
                if(mappings.getUser().getUserEmail().toLowerCase().contains(emailid.toLowerCase())){
                    mappings.getFavRecipes().getFavMappings().remove(mappings);
                }
            }
            userDao.deleteById(emailid);
        }
        return check;
    }
}
