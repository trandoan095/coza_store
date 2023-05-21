package com.cybersoft.cozastore.service;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.request.SignUpRequest;
import com.cybersoft.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
   private UserRepository userRepository;

    public boolean checklogin (String username, String password) {

       List<UserEntity> list =  userRepository.findByUsernameAndPassword(username,password);

        return list.size() > 0;

    }

    public boolean signup (SignUpRequest usersignup) {
        try{
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(usersignup.getUsername());
            userEntity.setPassword(usersignup.getPassword());
            userEntity.setEmail(usersignup.getEmail());
            userRepository.save(userEntity);

            return true;
        }
        catch (Exception e){

            return false;
        }


    }



}
