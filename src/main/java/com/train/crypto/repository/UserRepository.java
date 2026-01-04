package com.train.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.crypto.model.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    public Optional<User> findByUserDocument(String userDocument);

    public User save(Optional<User> user);
}
