package com.jan.testingoauth.repository;

import com.jan.testingoauth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    List<UserEntity> findAll();
    UserEntity findById(int id);
}
