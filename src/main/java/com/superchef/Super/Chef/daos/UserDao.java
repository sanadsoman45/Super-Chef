package com.superchef.Super.Chef.daos;


import com.superchef.Super.Chef.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,String> {

    User findByuserEmailAndPasswd(String emailid, String passwd);

    @Modifying
    @Query("Update User set user_email = :userEmail, user_name = :userName, user_passwd = :userPasswd where user_email = :newEmail")
    int updateUser(@Param("userEmail") String userEmail, @Param("userName") String userName,@Param("userPasswd") String userPasswd
            ,@Param("newEmail") String newEmail);

}
