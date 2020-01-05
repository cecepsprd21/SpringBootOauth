package com.jan.testingoauth.service;

import com.jan.testingoauth.model.UserEntity;
import com.jan.testingoauth.repository.UserRepository;
import com.jan.testingoauth.response.ResponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // GET ALL USER Service
    public ResponseEntity getAllUser() {
        ResponseDao responseDao = new ResponseDao();
        List<UserEntity> listUser = userRepository.findAll();
        responseDao.setData(listUser);
        return responseDao.List(responseDao);
    }

    //GET USER BY ID Service
    public ResponseEntity getUserById(int id){
        ResponseDao responseDao = new ResponseDao();
        UserEntity user = userRepository.findById(id);
        responseDao.setData(user);
        return responseDao.OK(responseDao);
    }

    // CREATE USER Service
    public ResponseEntity addUser(UserEntity user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        ResponseDao responseDao = new ResponseDao();
        responseDao.setData(user);
        return responseDao.created(responseDao);
    }

    // Update User Service
    public ResponseEntity updateUser(int id, UserEntity dataUser){
        ResponseDao responseDao = new ResponseDao();
        UserEntity user = userRepository.findById(id);
        if(user == null){
            return responseDao.notFound();
        }
        user.setFullName(dataUser.getFullName());
        user.setUsername(dataUser.getUsername());
        user.setPassword(dataUser.getPassword());
        user.setRole(dataUser.getRole());
        userRepository.save(user);
        responseDao.setData(user);
        return responseDao.updated(responseDao);
    }

    // Delete user service
    public ResponseEntity deleteUser(int id){
        UserEntity user = userRepository.findById(id);
        ResponseDao responseDao = new ResponseDao();
        if(user == null){
            return responseDao.notFound();
        }
        userRepository.delete(user);
        responseDao.setData(user);
        return responseDao.deleted(responseDao);
    }
}