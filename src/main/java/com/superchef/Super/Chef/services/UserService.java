package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    HashMap<String,String> getUserByEmailId(String emailid);

    boolean deleteUser(String emailid);

    boolean checkUserByEmailId(String emailId);

    int updateUser(User user, String emailid);

    String loginUser(String emailid, String passwd);



}
