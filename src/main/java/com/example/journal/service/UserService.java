package com.example.journal.service;

import com.example.journal.Repository.UserRepository;
import com.example.journal.entity.User;
import com.example.journal.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserService {

@Autowired
private UserRepository userRepository;


    public void saveEntry(User User){userRepository.save(User);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
    userRepository.deleteById(id);}

public User findByusername(String username){
    return userRepository.findByusername(username);
}}


