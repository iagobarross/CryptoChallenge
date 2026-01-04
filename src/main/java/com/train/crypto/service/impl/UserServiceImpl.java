package com.train.crypto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.train.crypto.model.User;
import com.train.crypto.repository.UserRepository;
import com.train.crypto.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByDocument(String userDocument) {
        return userRepository.findByUserDocument(userDocument);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setUserDocument(updatedUser.getUserDocument());
        user.setCreditCardToken(updatedUser.getCreditCardToken());
        user.setValue(updatedUser.getValue());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
