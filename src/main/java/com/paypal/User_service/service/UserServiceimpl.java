package com.paypal.User_service.service;

import com.paypal.User_service.entity.User;
import com.paypal.User_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    public UserServiceimpl(UserRepository userRepository){this.userRepository = userRepository;}


    @Override
    public User createUser(User user) {
        return (User) userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
