package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.UserEmailAlreadyExists;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        int updateCount;

        if(!result.isPresent()){
           throw new userNotFound("User Not Found for "+emailid);
        }
        else if(user.getUserEmail().equalsIgnoreCase(emailid)){
            updateCount = userDao.updateUser(user.getUserEmail(),user.getUserName(),user.getPasswd(),emailid);
        }
        else if(userDao.findById(user.getUserEmail()).isPresent()){
            throw new UserEmailAlreadyExists("User Email-Id Already exists.");
        }
        else{
            updateCount = userDao.updateUser(user.getUserEmail(),user.getUserName(),user.getPasswd(),emailid);
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

        if(!userDao.findById(emailid).isPresent()){
            check = false;
            throw new userNotFound("User Not Found "+emailid);
        }
        userDao.deleteById(emailid);
        return check;
    }






}
