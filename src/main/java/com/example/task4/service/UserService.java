package com.example.task4.service;

import com.example.task4.model.User;
import com.example.task4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        createUser(new User(null, "kolya", "kolya@mail.ru"));
        createUser(new User(null, "alina", "alina@mail.ru"));
        createUser(new User(null, "lexa", "lexa@mail.ru"));
        createUser(new User(null, "danya", "danya@mail.ru"));

        deleteUser(2L);

        Optional<User> userOpt = getUserById(3L);
        userOpt.ifPresent(System.out::println);

        updateUser(1L, "AlinaUpdated", "alina_updated@gmail.com");
        findByEmail("alina_updated@gmail.com").ifPresent(System.out::println);

        System.out.println("Exists 'alina': " + existsByLogin("alina"));

        List<User> users = findAllByLogin("alina");
        users.forEach(System.out::println);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            System.out.println("User with id " + id + " не найден");
        }
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, String login, String mail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setLogin(login);
        user.setMail(mail);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByMail(email);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public List<User> findAllByLogin(String login) {
        return userRepository.findAllByLogin(login);
    }
}