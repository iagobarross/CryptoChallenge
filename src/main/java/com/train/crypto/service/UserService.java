package com.train.crypto.service;

import java.util.List;
import java.util.Optional;

import com.train.crypto.model.User;

public interface UserService {
    
    public List<User> findAll();

    public Optional<User> findById(Long id);

    public Optional<User> findByDocument(String userDocument);

    public User create(User user) throws Exception;

    public User update(Long id, User updatedUser);

    public void deleteById(Long id);
}
