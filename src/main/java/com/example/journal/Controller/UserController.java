package com.example.journal.Controller;

import com.example.journal.entity.User;
import com.example.journal.entity.journalEntry;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
private UserService userService;

@GetMapping
public List<User>getAll(){ return userService.getAll();
}

@PostMapping
    public boolean createEntry(@RequestBody User user) {
        userService.saveEntry(user);
        return true;
}
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        User userInDb=userService.findByusername(user.getUsername());
        if(userInDb!= null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
