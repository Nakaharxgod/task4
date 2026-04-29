package com.example.task4.service;
import jakarta.annotation.PostConstruct;
import com.example.task4.model.User;
import com.example.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void run(){
        createUser();
        deleteUser(2L);
        getUserById(3L);
        updateUser(1L, "Nikolay", "qwertyui@gmail.com");
        findByEmail("qwertyui@gmail.com");
        existsByName("Nikolay");
        findAllByName("Nikolay");
    }


    public void createUser() {
        User user1 = new User("Nikolay", "mail@mail.com");
        User user2 =  new User("Nikolay", "mail1@gmail.com");
        User user3 =  new User("Daniel", "qwer@yandex.ru");
        User user4 =  new User("Dmitry", "ghjk@gmail.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, String login, String mail) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(login);
        user.setmail(mail);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByMail(email);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByLogin(name);
    }

    public List<User> findAllByName(String name) {
        return userRepository.findAllByLogin(name);
    }
}